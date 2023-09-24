package sube.interviews.mareoenvios.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sube.interviews.mareoenvios.bo.ProductBO;
import sube.interviews.mareoenvios.dto.ProductDTO;
import sube.interviews.mareoenvios.dto.ProductToReportDTO;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.ServiceException;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportsService {

    @Autowired
    ProductBO productBO;

    private static final Logger LOGGER = LogManager.getLogger(ReportsService.class);

    @GetMapping(value = "/top-sent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity topSent(){
        try {
            List<ProductToReportDTO> list = productBO.topSent();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error en el servidor.");
        }
    }

}
