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

    @Override
    public Boolean hasInProgressTask(Shipping shipping) throws RepositoryException {
        try{
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Task> cq = cb.createQuery(classz);
            Root<Task> root = cq.from(classz);
            cq.where(
                    cb.and(
                            cb.equal( root.get("shippingId"), shipping ),
                            cb.equal( root.get("state"), TaskStateEnum.IN_PROGRESS.getValue())
                    )
            );
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
    public List<Task> getPendingTaskList(Shipping shipping) throws RepositoryException {
        try{
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Task> cq = cb.createQuery(classz);
            Root<Task> root = cq.from(classz);
            cq.where(
                    cb.and(
                            cb.equal( root.get("shippingId"), shipping ),
                            cb.equal( root.get("state"), TaskStateEnum.PENDING.getValue())
                    )
            );
            return entityManager.createQuery(cq).getResultList();
        } catch (PersistenceException e){
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
