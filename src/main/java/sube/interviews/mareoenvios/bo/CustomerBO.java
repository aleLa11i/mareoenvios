package sube.interviews.mareoenvios.bo;

import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.dto.CustomerDTO;
import sube.interviews.mareoenvios.exception.BusinessException;

public interface CustomerBO {
    CustomerDTO getbyId( Long id ) throws BusinessException;
}
