package sube.interviews.mareoenvios.service;

import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sube.interviews.mareoenvios.bo.CustomerBO;
import sube.interviews.mareoenvios.bo.ShippingBO;
import sube.interviews.mareoenvios.dto.*;
import sube.interviews.mareoenvios.enums.ShippingStateEnum;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.ServiceException;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class ShippingService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);

    @Autowired
    ShippingBO shippingBO;

    @GetMapping(value = "/api/shippings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShipping(
            @Valid @Positive @PathVariable("id")  Long id
    ){
        try {
            ShippingDTO shippingDTO = shippingBO.getbyId(id);
            return ResponseEntity.status(HttpStatus.OK).body(shippingDTO);
        }catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error en el servidor.");
        }catch (NoResultException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(e.getMessage()));
        }
    }

    @GetMapping( value = "/api/status", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity getStatus(){
        try {
            List<ShippingStatesDTO> status = shippingBO.getStatus();
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error en el servidor.");
        }
    }

    @PatchMapping(value = "/api/shippings/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity patchShippingState(
            @Valid @Positive @PathVariable("id") Long id,
            @RequestBody ShippingStateRequestDTO body
    ){
        try {
            //TODO: Validar id, validar body, validar que existe el id ( se hace en repository )

            return ResponseEntity.status(HttpStatus.OK).body("");
//        }catch (BusinessException e){
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error en el servidor.");
        }catch (NoResultException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(e.getMessage()));
        }
    }
}
