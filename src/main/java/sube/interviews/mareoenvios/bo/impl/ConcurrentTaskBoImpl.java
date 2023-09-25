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
import sube.interviews.mareoenvios.service.clients.MareoEnviosAPI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class ConcurrentTaskBoImpl implements ConcurrentTaskBO {

    private final static Map<Long, Thread> threadMap = new HashMap<>();
    private final static Logger LOGGER = LogManager.getLogger(ConcurrentTaskBoImpl.class);

    @Autowired
    ShippingRepository shippingRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    MareoEnviosAPI mareoEnviosAPI;

    @Override
    public void runTask(ConcurrentTaskRequestDTO taskDTO) {
        taskDTO.getShippings().forEach(taskShippingDTO -> {
            try {
                Shipping shipping = shippingRepository.getById(taskShippingDTO.getShippingId());
                Task task = taskRepository.save(new Task(shipping, TaskStateEnum.IN_PROGRESS.getValue()));

                if (shipping.getState().equals(ShippingStateEnum.CANCELLED.getValue())) {
                    task.setState(TaskStateEnum.FAILED.getValue());
                    task.setError(String.format("El envío con ID='%s' se encuentra cancelado por lo que no se puede ejecutar una tarea.", shipping.getId()));
                    task.setDate(LocalDateTime.now());
                    taskRepository.update(task);
                    threadMap.remove(shipping.getId());
                    throw new BusinessException(String.format("El envío con ID='%s' se encuentra cancelado por lo que no se puede ejecutar una tarea. Se cancela tarea.", shipping.getId()));
                }
                if (shipping.getState().equals(ShippingStateEnum.DELIVERED.getValue())) {
                    task.setState(TaskStateEnum.FAILED.getValue());
                    task.setError(String.format("El envío con ID='%s' ya llego.", shipping.getId()));
                    task.setDate(LocalDateTime.now());
                    taskRepository.update(task);
                    threadMap.remove(shipping.getId());
                    throw new BusinessException(String.format("El envío con ID='%s' ya llego. Se cancela tarea.", shipping.getId()));
                }

                ArrayList<Long> idsThreadsList = new ArrayList<>(threadMap.keySet());
                if (idsThreadsList.contains(shipping.getId())) {
                    task.setState(TaskStateEnum.FAILED.getValue());
                    task.setError(String.format("El envío con ID='%s' ya posee una tarea ejecutandose", shipping.getId()));
                    task.setDate(LocalDateTime.now());
                    taskRepository.update(task);
                    threadMap.remove(shipping.getId());
                    throw new BusinessException(String.format("El envío con ID='%s' ya posee una tarea ejecutandose", shipping.getId()));
                } else {
                    Thread shippingTask = new Thread(() -> {
                        try {
                            this.executeTask(taskShippingDTO, shipping, task);
                        } catch (BusinessException e) {
                            e.printStackTrace();
                        }
                    });
                    shippingTask.start();
                    threadMap.put(shipping.getId(), shippingTask);
                }
            } catch (BusinessException e) {
                e.printStackTrace();
                threadMap.remove(taskShippingDTO.getShippingId());
            } catch (RepositoryException e) {
                e.printStackTrace();
                threadMap.remove(taskShippingDTO.getShippingId());
            }
        });
    }

    public void executeTask(ConcurrentTaskShippingDTO taskShippingDTO, Shipping shipping, Task task) throws BusinessException {
        TimerTask timingTask = new TimerTask() {
            public void run() {
                try {
                    LOGGER.info(String.format("Ejecutando tarea para el envio con ID='%s'", shipping.getId()));

                    changeState(taskShippingDTO.getNextState(), shipping);
                    mareoEnviosAPI.patchState(shipping.getId(), shipping.getState());

                    shippingRepository.update(shipping);

                    task.setState(TaskStateEnum.SUCCESS.getValue());
                    task.setDate(LocalDateTime.now());
                    taskRepository.update(task);
                    threadMap.remove(shipping.getId());

                    LOGGER.info(String.format("Se ejecuto correctamente tarea para el envio con ID='%s'", shipping.getId()));
                } catch (NoResultException | BusinessException | RepositoryException | IOException e) {
                    threadMap.remove(shipping.getId());
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer("Timer");
        long delay = taskShippingDTO.getTimeStartInSeg() * 1000; //El timer toma en milisec por eso hay que hacer el pasaje
        timer.schedule(timingTask, delay);
    }

    public void changeState(Boolean nextState, Shipping shipping) throws BusinessException {
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
                }
            }
        } catch (IllegalArgumentException e) {
            throw new BusinessException(String.format("El envio con ID='%s' posee un valor de estado = '%s' que es invalido", shipping.getId(), shipping.getState()));
        }
    }
}
