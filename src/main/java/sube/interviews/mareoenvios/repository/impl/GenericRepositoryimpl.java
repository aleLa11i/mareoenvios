package sube.interviews.mareoenvios.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.repository.GenericRepository;

import java.util.List;

public class GenericRepositoryimpl<T> implements GenericRepository<T> {
    private final static Logger LOGGER = LogManager.getLogger(GenericRepositoryimpl.class);

    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<T> classz;

    public GenericRepositoryimpl(){}

    public GenericRepositoryimpl(Class<T> classz){ this.classz = classz; }

    @Override
    public T getById(Long id) throws RepositoryException {
        try{
            LOGGER.info(String.format("Buscando '%s' con ID='%s'...", classz.getSimpleName(), id));
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(classz);
            Root<T> root = cq.from(classz);
            cq.where(
                    cb.equal( root.get("id"), id )
            );
            return entityManager.createQuery(cq).getSingleResult();
        } catch (NoResultException e){
            e.printStackTrace();
            throw new NoResultException(String.format("No se encuentra '%s' con ID='%s'", classz.getSimpleName(), id));
        } catch (PersistenceException e){
            e.printStackTrace();
            throw new RepositoryException(String.format("Error al buscar '%s' con ID='%s'", classz.getSimpleName(), id));
        }
    }

    @Override
    public List<T> getList() throws RepositoryException {
        try{
            LOGGER.info(String.format("Buscando listado de '%s' ...", classz.getSimpleName()));
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(classz);
            return entityManager.createQuery(cq).getResultList();
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new RepositoryException(String.format("Error al buscar listado de entidades '%s'", classz.getSimpleName()));
        }
    }

    @Override
    public void update(T entity) throws RepositoryException {
        try {
            LOGGER.info(String.format("Actualizando '%s' ...", classz.getSimpleName()));
            entityManager.merge(entity);
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new RepositoryException(String.format("Error al actualizar '%s'", classz.getSimpleName()));
        }
    }
}
