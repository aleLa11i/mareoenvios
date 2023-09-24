package sube.interviews.mareoenvios.bo;

import sube.interviews.mareoenvios.dto.ProductDTO;
import sube.interviews.mareoenvios.dto.ProductToReportDTO;
import sube.interviews.mareoenvios.exception.BusinessException;

import java.util.List;

public interface ProductBO {

    List<ProductToReportDTO> topSent() throws BusinessException;
}
