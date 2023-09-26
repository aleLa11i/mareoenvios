package sube.interviews.mareoenvios.repository.impl;

import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.entity.Product;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.repository.ProductRepository;

import java.util.List;

@Component
public class ProductRepositoryImpl extends GenericRepositoryImpl<Product> implements ProductRepository{
    public ProductRepositoryImpl() { super(Product.class); }

    @Override
    public List<Product> getList() throws RepositoryException {
        return super.getList();
    }
}
