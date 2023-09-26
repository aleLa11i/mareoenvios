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
import sube.interviews.mareoenvios.bo.ShippingBO;
import sube.interviews.mareoenvios.dto.*;
import sube.interviews.mareoenvios.exception.BusinessException;

import java.util.List;

@RestController
public class ShippingService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);

    @Autowired
    ShippingBO shippingBO;

    @GetMapping(value = "/shippings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShipping(@PathVariable("id")  Long id){
        try {
            ShippingDTO shippingDTO = shippingBO.getbyId(id);
            return ResponseEntity.status(HttpStatus.OK).body(shippingDTO);
        }catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }catch (NoResultException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(e.getMessage()));
        }
    }

    @GetMapping( value = "/status", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity getStatus(){
        try {
            List<ShippingStatesDTO> status = shippingBO.getStatus();
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
