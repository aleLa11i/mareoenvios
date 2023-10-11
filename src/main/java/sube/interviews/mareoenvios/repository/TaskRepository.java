package sube.interviews.mareoenvios.repository;

import sube.interviews.mareoenvios.entity.Shipping;
import sube.interviews.mareoenvios.entity.Task;
import sube.interviews.mareoenvios.exception.RepositoryException;

import java.util.List;

public interface TaskRepository extends GenericRepository<Task>{

    Boolean hasInProgressTask(Shipping shipping) throws RepositoryException;

    Boolean hasPendingOrInProcessTasks() throws RepositoryException;

    List<Task> getPendingTaskList(Shipping shipping) throws RepositoryException;

    Task getPendingTask(Shipping shipping ) throws RepositoryException;
}
