package sube.interviews.mareoenvios.bo;

import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.dto.ConcurrentTaskRequestDTO;
import sube.interviews.mareoenvios.exception.BusinessException;

public interface ConcurrentTaskBO {
    void runTask( ConcurrentTaskRequestDTO task );
}
