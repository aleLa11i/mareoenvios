package sube.interviews.mareoenvios.service;

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
import sube.interviews.mareoenvios.bo.ShippingBO;
import sube.interviews.mareoenvios.dto.CustomerDTO;
import sube.interviews.mareoenvios.dto.ShippingDTO;
import sube.interviews.mareoenvios.dto.ShippingStatesDTO;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.ServiceException;

import java.util.List;

@RestController
public class ShippingService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);

    @Autowired
    ShippingBO shippingBO;

    @GetMapping(value = "/api/shippings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShippingDTO> getCustomer(@PathVariable("id") Long id ) throws ServiceException {
        try {
            ShippingDTO shippingDTO = shippingBO.getbyId(id);
            return ResponseEntity.status(HttpStatus.OK).body(shippingDTO);
        }catch (BusinessException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @GetMapping( value = "/api/status", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<ShippingStatesDTO>> getStatus() throws ServiceException {
        try {
            List<ShippingStatesDTO> status = shippingBO.getStatus();
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (BusinessException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
