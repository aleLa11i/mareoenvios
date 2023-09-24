package sube.interviews.mareoenvios.bo.impl;

import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.bo.CustomerBO;
import sube.interviews.mareoenvios.dto.CustomerDTO;
import sube.interviews.mareoenvios.entity.Customer;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.mapper.CustomerMapper;
import sube.interviews.mareoenvios.repository.CustomerRepository;

@Component
public class CustomerBOImpl implements CustomerBO {

    private final static Logger LOGGER = LogManager.getLogger(CustomerBOImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerDTO getbyId(Long id) throws BusinessException, NoResultException {
        try {
            Customer customer = customerRepository.getById(id);
            return CustomerMapper.INSTANCE.mapEntityToDto(customer);
        } catch (RepositoryException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (NoResultException e) {
            throw e;
        } catch (Exception e){
            throw new BusinessException(e.getMessage(),e);
        }
    }
}
