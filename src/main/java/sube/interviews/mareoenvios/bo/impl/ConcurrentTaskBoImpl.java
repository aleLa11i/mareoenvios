package sube.interviews.mareoenvios.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.bo.ConcurrentTaskBO;
import sube.interviews.mareoenvios.dto.ConcurrentTaskRequestDTO;
import sube.interviews.mareoenvios.entity.Shipping;
import sube.interviews.mareoenvios.entity.Task;
import sube.interviews.mareoenvios.enums.ShippingStateEnum;
import sube.interviews.mareoenvios.enums.TaskStateEnum;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.repository.ShippingRepository;
import sube.interviews.mareoenvios.repository.TaskRepository;
import sube.interviews.mareoenvios.service.clients.MareoEnviosAPI;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.*;

@Component
public class ConcurrentTaskBoImpl implements ConcurrentTaskBO {

    private final static Logger LOGGER = LogManager.getLogger(ConcurrentTaskBoImpl.class);

    @Value("${max-time-task}")
    private String maxTimeTask;

    @Autowired
    ShippingRepository shippingRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    MareoEnviosAPI mareoEnviosAPI;

    @Override
    public void runTask(ConcurrentTaskRequestDTO taskDTO) throws BusinessException{
        taskDTO.getShippings().forEach(taskShippingDTO -> {
            try {
                Shipping shipping = shippingRepository.getById(taskShippingDTO.getShippingId());
                Task task = taskRepository.save(new Task(shipping, TaskStateEnum.PENDING.getValue(), LocalDateTime.now(), taskShippingDTO.getNextState(), taskShippingDTO.getTimeStartInSeg()));

                if(taskShippingDTO.getTimeStartInSeg() > Integer.parseInt(maxTimeTask)){
                    cancelTask(task,String.format("La tarea del envío con ID='%s' supera el tiempo maximo de %s segundos.",  shipping.getId(), maxTimeTask));
                    return;
                }
                if (taskRepository.hasInProgressTask(shipping)) {
                    cancelTask(task, String.format("El envío con ID='%s' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.", shipping.getId()));
                    taskRepository.save(new Task(shipping, TaskStateEnum.PENDING.getValue(), LocalDateTime.now(), taskShippingDTO.getNextState(), taskShippingDTO.getTimeStartInSeg()));
                    return;
                }

                new Thread(() -> {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    try {
                        Future<?> future;
                        Task actualTask = task;
                        do{
                            Task finalActualTask = actualTask; //Se debe crear una variables actualTask final para poder utilizarla dentro de método runnable
                            future = executor.submit(() -> {
                                try {
                                    this.executeTask( finalActualTask.getTimeStart(), finalActualTask.getNextState(), finalActualTask.getShippingId(), finalActualTask);
                                } catch (BusinessException e) {
                                    e.printStackTrace();
                                }
                            });
                            future.get( Integer.parseInt(maxTimeTask), TimeUnit.SECONDS);
                            actualTask = taskRepository.getPendingTask(shipping);

                        }while(!taskRepository.getPendingTaskList(shipping).isEmpty());

                        future.get();
                        if( !taskRepository.hasPendingOrInProcessTasks() ){
                            LOGGER.info("----------YA NO HAY MAS TAREAS PENDIENTES----------");
                        }

                    } catch (InterruptedException | ExecutionException | RepositoryException | TimeoutException e) {
                        e.printStackTrace();
                    }  finally {
                        executor.shutdown();
                    }
                }).start();
            } catch (BusinessException | RepositoryException e) {
                e.printStackTrace();
            }
        });
    }

    private void executeTask(Long timeStart, Boolean nextState, Shipping shipping, Task task) throws BusinessException {
        try{
            task.setState(TaskStateEnum.IN_PROGRESS.getValue());
            taskRepository.update(task);
            TimeUnit.SECONDS.sleep(timeStart);

            LOGGER.info(String.format("Ejecutando tarea para el envio con ID='%s'", shipping.getId()));

            changeState(nextState, shipping, task);
            mareoEnviosAPI.patchState(shipping.getId(), shipping.getState());

            shippingRepository.update(shipping);

            task.setState(TaskStateEnum.SUCCESS.getValue());
            task.setEndDate(LocalDateTime.now());
            taskRepository.update(task);

            LOGGER.info(String.format("Se ejecuto correctamente tarea para el envio con ID='%s'", shipping.getId()));
        }catch (RepositoryException | InterruptedException | IOException e ){
            throw new BusinessException(e.getMessage(), e);
        }
    }

    private void changeState(Boolean nextState, Shipping shipping, Task task) throws BusinessException {
        try {
            if (!nextState) {
                shipping.setState(ShippingStateEnum.CANCELLED.getValue());
            } else {
                ShippingStateEnum state = ShippingStateEnum.getState(shipping.getState());
                switch (state) {
                    case INITIAL -> shipping.setState(ShippingStateEnum.SEND_TO_MAIL.getValue());
                    case SEND_TO_MAIL -> shipping.setState(ShippingStateEnum.IN_TRAVEL.getValue());
                    case IN_TRAVEL -> {
                        shipping.setState(ShippingStateEnum.DELIVERED.getValue());
                        shipping.setArriveDate(Date.valueOf(LocalDate.now())); //Si el envío cambia a "Entregado" hay que registrar la fecha de llegada
                    }
                    case CANCELLED -> {
                        cancelTask(task,String.format("El envío con ID='%s' se encuentra cancelado.", shipping.getId()));
                        throw new BusinessException(String.format("El envío con ID='%s' se encuentra cancelado.", shipping.getId()));
                    }
                    case DELIVERED -> {
                        cancelTask(task,String.format("El envío con ID='%s' ya llego.", shipping.getId()));
                        throw new BusinessException(String.format("El envío con ID='%s' ya llego.", shipping.getId()));
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new BusinessException(String.format("El envio con ID='%s' posee un valor de estado = '%s' que es invalido", shipping.getId(), shipping.getState()));
        }
    }

    private void cancelTask(Task task, String message) throws BusinessException{
        try{
            task.setState(TaskStateEnum.FAILED.getValue());
            task.setError(message);
            task.setEndDate(LocalDateTime.now());
            taskRepository.update(task);
            LOGGER.error(message);
        }catch (RepositoryException e){
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
