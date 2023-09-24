package sube.interviews.mareoenvios.bo.impl;

import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.bo.ConcurrentTaskBO;
import sube.interviews.mareoenvios.dto.ConcurrentTaskRequestDTO;
import sube.interviews.mareoenvios.dto.ConcurrentTaskShippingDTO;
import sube.interviews.mareoenvios.entity.Shipping;
import sube.interviews.mareoenvios.entity.Task;
import sube.interviews.mareoenvios.enums.ShippingStateEnum;
import sube.interviews.mareoenvios.enums.TaskStateEnum;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.repository.ShippingRepository;
import sube.interviews.mareoenvios.repository.TaskRepository;
import sube.interviews.mareoenvios.utils.ThreadsUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConcurrentTaskBoImpl implements ConcurrentTaskBO {

    private final static Logger LOGGER = LogManager.getLogger(ConcurrentTaskBoImpl.class);

    @Autowired
    private ThreadsUtils threadsUtils;

    @Autowired
    ShippingRepository shippingRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void runTask(ConcurrentTaskRequestDTO taskDTO){
        taskDTO.getShippings().forEach( shippingDTO ->{
            try {
                Shipping shipping = shippingRepository.getById(shippingDTO.getShippingId());
                Task task = taskRepository.save(new Task( shipping, TaskStateEnum.IN_PROGRESS.getValue()));

                if(shipping.getState().equals(ShippingStateEnum.CANCELLED.getValue())){
                    task.setState(TaskStateEnum.FAILED.getValue());
                    task.setError(String.format("El envío con ID='%s' se encuentra cancelado por lo que no se puede ejecutar una tarea.",shipping.getId()));
                    taskRepository.update(task);
                    throw new BusinessException(String.format("El envío con ID='%s' se encuentra cancelado por lo que no se puede ejecutar una tarea. Se cancela tarea.",shipping.getId()));
                }
                if(shipping.getState().equals(ShippingStateEnum.DELIVERED.getValue())){
                    task.setState(TaskStateEnum.FAILED.getValue());
                    task.setError(String.format("El envío con ID='%s' ya llego.",shipping.getId()));
                    taskRepository.update(task);
                    throw new BusinessException(String.format("El envío con ID='%s' ya llego. Se cancela tarea.",shipping.getId()));
                }

                Boolean hasThread = threadsUtils.getExecutedThreads().stream()
                        .map( thread -> thread.getName() )
                        .collect(Collectors.toList())
                        .contains("Tarea Shipping "+shippingDTO.getShippingId()); // verificamos que no tenga algun thread en curso
                LOGGER.info( hasThread );
                LOGGER.info(threadsUtils.getExecutedThreads().stream()
                        .map( thread -> thread.getName() )
                        .collect(Collectors.toList()));

                if( hasThread ){
                    task.setState(TaskStateEnum.FAILED.getValue());
                    task.setError(String.format("El envío con ID='%s' ya posee una tarea ejecutandose",shippingDTO.getShippingId()));
                    taskRepository.update(task);
                    throw new BusinessException(String.format("El envío con ID='%s' ya posee una tarea ejecutandose",shippingDTO.getShippingId()));
                } else {
                    Thread shippingTask = new Thread(() -> {
                        try{
                            this.executeTask( shippingDTO, shipping, task );
                        }catch (BusinessException e){
                            e.printStackTrace();
                        }
                    });
                    shippingTask.setName("Tarea Shipping "+shippingDTO.getShippingId());
                    shippingTask.start();
                }
            } catch ( BusinessException e ){
                e.printStackTrace();
            } catch (RepositoryException e){
                e.printStackTrace();
            }
        });
    }

    public void executeTask( ConcurrentTaskShippingDTO shippingDTO, Shipping shipping, Task task ) throws BusinessException{
        TimerTask timingTask = new TimerTask() {
            public void run() {
                try {
                    LOGGER.info(String.format("Ejecutando tarea para el envio con ID='%s'", shippingDTO.getShippingId()));
                    changeState( shippingDTO.getNextState(), shipping);
                    shippingRepository.update(shipping);
                    task.setState(TaskStateEnum.SUCCESS.getValue());
                    taskRepository.update(task);
                    LOGGER.info(String.format("Se ejecuto correctamente tarea para el envio con ID='%s'", shipping.getId()));
                } catch ( NoResultException | BusinessException | RepositoryException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer("Timer");
        Long delay = shippingDTO.getTimeStartInSeg()*1000; //El timer toma en milisec por eso hay que hacer el pasaje
        timer.schedule(timingTask, delay );
    }

    public void changeState( Boolean nextState, Shipping shipping ) throws BusinessException{
        try{
            if(!nextState){
                shipping.setState(ShippingStateEnum.CANCELLED.getValue());
            } else {
                ShippingStateEnum state = ShippingStateEnum.getState(shipping.getState());
                switch (state){
                    case INITIAL -> shipping.setState(ShippingStateEnum.SEND_TO_MAIL.getValue());
                    case SEND_TO_MAIL -> shipping.setState(ShippingStateEnum.IN_TRAVEL.getValue());
                    case IN_TRAVEL -> {
                        shipping.setState(ShippingStateEnum.DELIVERED.getValue());
                        LocalDate today = LocalDate.now();
                        shipping.setArriveDate(Date.valueOf(today)); //Si el envío cambia a "Entregado" hay que registrar la fecha de llegada
                    }
                }
            }
        }catch (IllegalArgumentException e){
            throw new BusinessException(String.format("El envio con ID='%s' posee un valor de estado = '%s' que es invalido", shipping.getId(), shipping.getState()));
        }
    }
}
