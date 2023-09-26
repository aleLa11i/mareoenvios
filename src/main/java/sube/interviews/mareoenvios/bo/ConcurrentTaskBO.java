package sube.interviews.mareoenvios.bo;

import sube.interviews.mareoenvios.dto.ConcurrentTaskRequestDTO;
import sube.interviews.mareoenvios.exception.BusinessException;

public interface ConcurrentTaskBO {
    void runTask( ConcurrentTaskRequestDTO task ) throws BusinessException;
}
