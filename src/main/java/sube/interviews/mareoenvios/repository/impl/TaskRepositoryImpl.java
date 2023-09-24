package sube.interviews.mareoenvios.repository.impl;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import sube.interviews.mareoenvios.entity.Shipping;
import sube.interviews.mareoenvios.entity.Task;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.repository.TaskRepository;

@Component
public class TaskRepositoryImpl extends GenericRepositoryImpl<Task> implements TaskRepository {

    public TaskRepositoryImpl() { super(Task.class); }
    private final static Logger LOGGER = LogManager.getLogger(TaskRepositoryImpl.class);

    @Override
    public Task getByShipping(Shipping shipping) throws RepositoryException {
        try{
            LOGGER.info(String.format("Buscando Task con Shipping ID='%s'...", shipping.getId()));
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Task> cq = cb.createQuery(Task.class);
            Root<Task> root = cq.from(Task.class);
            cq.where(
                    cb.equal( root.get("shippingId"), shipping )
            );
            return entityManager.createQuery(cq).getSingleResult();
        } catch (PersistenceException e){
            throw new RepositoryException(e.getMessage(), e);
        } catch (Exception e){
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
