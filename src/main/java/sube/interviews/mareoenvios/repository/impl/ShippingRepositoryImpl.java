package sube.interviews.mareoenvios.repository.impl;

import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.entity.Customer;
import sube.interviews.mareoenvios.entity.Shipping;
import sube.interviews.mareoenvios.repository.GenericRepository;
import sube.interviews.mareoenvios.repository.ShippingRepository;

@Component
public class ShippingRepositoryImpl extends GenericRepositoryImpl<Shipping> implements ShippingRepository {
    public ShippingRepositoryImpl() { super(Shipping.class); }
}
