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
import sube.interviews.mareoenvios.enums.ShippingStateEnum;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.repository.ShippingRepository;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class ConcurrentTaskBoImpl implements ConcurrentTaskBO {

    private final static Logger LOGGER = LogManager.getLogger(ConcurrentTaskBoImpl.class);

    @Autowired
    ShippingRepository shippingRepository;

    @Override
    public void runTask(ConcurrentTaskRequestDTO task){
        task.getShippings().forEach( shipping ->{
            new Thread(() -> {
                try{
                    this.executeTask( shipping );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        });
    }

    public void executeTask( ConcurrentTaskShippingDTO shippingDTO ) throws BusinessException{
        try {
            Shipping shipping = shippingRepository.getById(shippingDTO.getShippingId());
            if(shipping.getState().equals(ShippingStateEnum.CANCELLED.getValue())){
                throw new BusinessException(String.format("El envío con ID='%s' se encuentra cancelado por lo que no se puede ejecutar una tarea",shipping.getId()));
            }
            if(shipping.getState().equals(ShippingStateEnum.DELIVERED.getValue())){
                throw new BusinessException(String.format("El envío con ID='%s' ya llego",shipping.getId()));
            }
            TimerTask task = new TimerTask() {
                public void run() {
                    try {
                        LOGGER.info(String.format("Ejecutando tarea para el envio con ID='%s'", shippingDTO.getShippingId()));
                        changeState( shippingDTO.getNextState(), shipping);
                    } catch ( NoResultException | BusinessException e) {
                        e.printStackTrace();
                    }
                }
            };
            Timer timer = new Timer("Timer");
            Long delay = shippingDTO.getTimeStartInSeg()*1000; //El timer toma en milisec por eso hay que hacer el pasaje
            timer.schedule(task, delay );
        }catch (RepositoryException e){
            e.printStackTrace();
        }
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
                    case IN_TRAVEL -> shipping.setState(ShippingStateEnum.DELIVERED.getValue());
                }
            }
            shippingRepository.update(shipping);
            LOGGER.info(String.format("Se ejecuto correctamente tarea para el envio con ID='%s'", shipping.getId()));
        }catch (RepositoryException e){
            throw new BusinessException(e.getMessage(), e);
        }catch (IllegalArgumentException e){
            throw new BusinessException(String.format("El envio con ID='%s' posee un valor de estado = '%s' que es invalido", shipping.getId(), shipping.getState()));
        }
    }
}
