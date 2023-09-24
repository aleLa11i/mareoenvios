package sube.interviews.mareoenvios.repository;

import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.entity.Shipping;
import sube.interviews.mareoenvios.entity.Task;
import sube.interviews.mareoenvios.exception.RepositoryException;

@Component
public interface TaskRepository extends GenericRepository<Task>{
}
