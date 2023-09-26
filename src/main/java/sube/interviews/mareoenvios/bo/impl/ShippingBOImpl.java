package sube.interviews.mareoenvios.bo.impl;

import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.bo.ShippingBO;
import sube.interviews.mareoenvios.dto.ShippingDTO;
import sube.interviews.mareoenvios.dto.ShippingStatesDTO;
import sube.interviews.mareoenvios.entity.Shipping;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.mapper.ShippingMapper;
import sube.interviews.mareoenvios.repository.ShippingRepository;

import java.util.List;

@Component
public class ShippingBOImpl implements ShippingBO {

    private final static Logger LOGGER = LogManager.getLogger(ShippingBOImpl.class);

    @Autowired
    ShippingRepository shippingRepository;

    @Autowired
    ShippingMapper shippingMapper;

    @Override
    public ShippingDTO getbyId(Long id) throws BusinessException, NoResultException {
        try {
            Shipping shipping = shippingRepository.getById(id);
            return shippingMapper.mapEntityToDto(shipping);
        } catch (RepositoryException e){
            throw new BusinessException(e.getMessage(), e);
        } catch (NoResultException e){
            throw e;
        } catch (Exception e){
            throw new BusinessException(e.getMessage(),e);
        }
    }

    @Override
    public List<ShippingStatesDTO> getStatus() throws BusinessException {
        try {
            List<Shipping> list = shippingRepository.getList();
            return shippingMapper.mapListToStateDtoList(list);
        } catch (RepositoryException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
