package sube.interviews.mareoenvios.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.bo.ProductBO;
import sube.interviews.mareoenvios.dto.ProductToReportDTO;
import sube.interviews.mareoenvios.exception.BusinessException;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.mapper.ProductMapper;
import sube.interviews.mareoenvios.repository.ProductRepository;

import java.util.Collections;
import java.util.List;

@Component
public class ProductBOImpl implements ProductBO {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductToReportDTO> topSent() throws BusinessException {
        try {
            List<ProductToReportDTO> productList = productMapper.mapListToProductToReportDTO(productRepository.getList());
            Collections.sort(productList, Collections.reverseOrder());
            return productList.subList(0,3);
        }catch (RepositoryException e){
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
