package sube.interviews.mareoenvios.bo;

import sube.interviews.mareoenvios.dto.CustomerDTO;
import sube.interviews.mareoenvios.exception.BusinessException;

public interface CustomerBO {
    CustomerDTO getbyId( Long id ) throws BusinessException;
}
