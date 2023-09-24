package sube.interviews.mareoenvios.repository.impl;

import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.entity.Customer;
import sube.interviews.mareoenvios.repository.CustomerRepository;

@Component
public class CustomerRepositoryImpl extends GenericRepositoryImpl<Customer> implements CustomerRepository {
    public CustomerRepositoryImpl() { super(Customer.class); }
}
