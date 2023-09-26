package sube.interviews.mareoenvios.service;

import jakarta.persistence.NoResultException;
import jakarta.validation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sube.interviews.mareoenvios.bo.ConcurrentTaskBO;
import sube.interviews.mareoenvios.dto.ConcurrentTaskRequestDTO;
import sube.interviews.mareoenvios.dto.ConcurrentTaskShippingDTO;
import sube.interviews.mareoenvios.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConcurrentTaskService {

    private static final Logger LOGGER = LogManager.getLogger(ConcurrentTaskService.class);
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = factory.getValidator();

    @Autowired
    ConcurrentTaskBO concurrentTaskBO;

    @PostMapping( value = "/concurrent-task", consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity runTask(@RequestBody ConcurrentTaskRequestDTO body){

        List<String> violations = new ArrayList<>();
        for ( ConcurrentTaskShippingDTO shippingDTO : body.getShippings() ) {
            for ( ConstraintViolation violation : validator.validate(shippingDTO)){
                violations.add(violation.getMessage());
            }
        }
        if( !violations.isEmpty() ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(violations); //Tomo el listado de violaciones atrapadas por el validador y los devuelvo en un http response
        }

        try {
            concurrentTaskBO.runTask(body);
            return ResponseEntity.status(HttpStatus.OK).body("Comenzó el proceso de ejecución de tareas...");
        }catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }catch (NoResultException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
