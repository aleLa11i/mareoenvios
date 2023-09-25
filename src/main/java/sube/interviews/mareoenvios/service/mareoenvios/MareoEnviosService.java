package sube.interviews.mareoenvios.service.mareoenvios;

import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sube.interviews.mareoenvios.dto.ShippingStateRequestDTO;
import sube.interviews.mareoenvios.enums.ShippingStateEnum;
import sube.interviews.mareoenvios.enums.TaskStateEnum;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.service.CustomerService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class MareoEnviosService {

//    ######################### Este servicio se utiliza con fines practicos para simular el servicio patch al que se llama #####################

    private static final Logger LOGGER = LogManager.getLogger(MareoEnviosService.class);

    @PatchMapping(value = "/api/shippings/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> patchShippingState(
            @Valid @Positive @PathVariable("id") Long id,
            @RequestBody ShippingStateRequestDTO body
    ){
        try {
            ShippingStateEnum state = ShippingStateEnum.getState(body.getTransition());
            long time = 0;

            switch (state){
                case SEND_TO_MAIL -> time = 1000;
                case IN_TRAVEL -> time = 3000;
                case DELIVERED -> time = 5000;
                case CANCELLED -> time = 3000;
            }

            Thread.sleep(time);
            return ResponseEntity.status(HttpStatus.OK).body("Estado actualizado correctamente");
        }catch (InterruptedException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error en el servidor.");
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado no valido");
        }
    }
}
