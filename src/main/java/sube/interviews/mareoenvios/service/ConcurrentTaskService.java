package sube.interviews.mareoenvios.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sube.interviews.mareoenvios.bo.ConcurrentTaskBO;
import sube.interviews.mareoenvios.bo.CustomerBO;
import sube.interviews.mareoenvios.dto.ConcurrentTaskRequestDTO;
import sube.interviews.mareoenvios.dto.ShippingDTO;
import sube.interviews.mareoenvios.dto.ShippingStatesDTO;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.ServiceException;

import java.util.List;
import java.util.Set;

@RestController
public class ConcurrentTaskService {

    private static final Logger LOGGER = LogManager.getLogger(ConcurrentTaskService.class);
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = factory.getValidator();

    @Autowired
    ConcurrentTaskBO concurrentTaskBO;

    @PostMapping( value = "/api/concurrent-task", consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity runTask(@RequestBody ConcurrentTaskRequestDTO body) throws ServiceException {

        Set<ConstraintViolation<ConcurrentTaskRequestDTO>> violations = validator.validate(body);
        if( !violations.isEmpty() ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(violations);
        }
        try {
            concurrentTaskBO.runTask(body);
            return ResponseEntity.status(HttpStatus.OK).body("Comenzó el proceso de ejecución de tareas...");
        }catch (BusinessException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
