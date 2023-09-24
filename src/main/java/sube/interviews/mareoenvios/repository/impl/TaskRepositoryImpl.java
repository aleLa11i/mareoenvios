package sube.interviews.mareoenvios.repository.impl;

import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.entity.Task;
import sube.interviews.mareoenvios.repository.TaskRepository;

@Component
public class TaskRepositoryImpl extends GenericRepositoryImpl<Task> implements TaskRepository {
    public TaskRepositoryImpl() { super(Task.class); }
}
