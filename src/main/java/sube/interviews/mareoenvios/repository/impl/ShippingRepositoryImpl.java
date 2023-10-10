package sube.interviews.mareoenvios.repository.impl;

import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.entity.Shipping;
import sube.interviews.mareoenvios.entity.Task;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.repository.ShippingRepository;

import java.util.List;

@Component
public class ShippingRepositoryImpl extends GenericRepositoryImpl<Shipping> implements ShippingRepository {
    public ShippingRepositoryImpl() { super(Shipping.class); }
}
