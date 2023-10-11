package sube.interviews.mareoenvios.repository.impl;

import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.entity.Shipping;
import sube.interviews.mareoenvios.entity.Task;
import sube.interviews.mareoenvios.enums.TaskStateEnum;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.repository.TaskRepository;

import java.util.List;

@Component
public class TaskRepositoryImpl extends GenericRepositoryImpl<Task> implements TaskRepository {
    public TaskRepositoryImpl() { super(Task.class); }

    private CriteriaQuery<Task> getCriteriaQueryTask(Shipping shipping, TaskStateEnum state) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(classz);
        Root<Task> root = cq.from(classz);
        cq.where(
                cb.and(
                        cb.equal( root.get("shippingId"), shipping ),
                        cb.equal( root.get("state"), state.getValue())
                )
        );
        return cq;
    }

    @Override
    public Boolean hasInProgressTask(Shipping shipping) throws RepositoryException {
        try{
            CriteriaQuery<Task> cq = getCriteriaQueryTask(shipping, TaskStateEnum.IN_PROGRESS);
            List<Task> list = entityManager.createQuery(cq).getResultList();
            return !list.isEmpty();
        } catch (NoResultException e){
            e.printStackTrace();
            throw new NoResultException(String.format("No se encuentra '%s' con ID='%s'", classz.getSimpleName(), shipping.getId()));
        } catch (PersistenceException e){
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean hasPendingOrInProcessTasks() throws RepositoryException {
        try{
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Task> cq = cb.createQuery(classz);
            Root<Task> root = cq.from(classz);
            cq.where(
                    cb.or(
                            cb.equal( root.get("state"), TaskStateEnum.PENDING.getValue()),
                            cb.equal( root.get("state"), TaskStateEnum.IN_PROGRESS.getValue())
                    )
            );
            List<Task> list = entityManager.createQuery(cq).getResultList();
            return !list.isEmpty();
        } catch (PersistenceException e){
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public List<Task> getPendingTaskList(Shipping shipping) throws RepositoryException {
        try{
            CriteriaQuery<Task> cq = getCriteriaQueryTask(shipping, TaskStateEnum.PENDING);
            return entityManager.createQuery(cq).getResultList();
        } catch (PersistenceException e){
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public Task getPendingTask(Shipping shipping) throws RepositoryException {
        try{
            CriteriaQuery<Task> cq = getCriteriaQueryTask(shipping, TaskStateEnum.PENDING);
            List<Task> list = entityManager.createQuery(cq).getResultList();
            if(!list.isEmpty()){
                return entityManager.createQuery(cq).getResultList().get(0);
            }
            return null;
        } catch (PersistenceException e){
            throw new RepositoryException(e.getMessage(), e);
        }
    }


}
