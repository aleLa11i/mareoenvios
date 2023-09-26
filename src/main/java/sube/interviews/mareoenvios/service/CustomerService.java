package sube.interviews.mareoenvios.service;

import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sube.interviews.mareoenvios.bo.CustomerBO;
import sube.interviews.mareoenvios.dto.CustomerDTO;
import sube.interviews.mareoenvios.exception.BusinessException;

@RestController
@RequestMapping("/customers")
public class CustomerService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);

    @Autowired
    CustomerBO customerBO;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCustomer(@PathVariable("id") Long id ){
        try {
            CustomerDTO customerDTO = customerBO.getbyId(id);
            return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
        }catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }catch (NoResultException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
