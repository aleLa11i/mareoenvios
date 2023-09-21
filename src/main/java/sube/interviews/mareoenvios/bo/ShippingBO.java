package sube.interviews.mareoenvios.bo;

import sube.interviews.mareoenvios.dto.ShippingDTO;
import sube.interviews.mareoenvios.dto.ShippingStatesDTO;
import sube.interviews.mareoenvios.exception.BusinessException;

import java.util.List;

public interface ShippingBO {
    ShippingDTO getbyId(Long id) throws BusinessException;

    List<ShippingStatesDTO> getStatus() throws BusinessException;
}
