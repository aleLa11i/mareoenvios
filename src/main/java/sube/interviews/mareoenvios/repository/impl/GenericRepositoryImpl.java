package sube.interviews.mareoenvios.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sube.interviews.mareoenvios.exception.RepositoryException;
import sube.interviews.mareoenvios.repository.GenericRepository;

import java.util.List;

public class GenericRepositoryImpl<T> implements GenericRepository<T> {
    private final static Logger LOGGER = LogManager.getLogger(GenericRepositoryImpl.class);

    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<T> classz;

    public GenericRepositoryImpl(){}

    public GenericRepositoryImpl(Class<T> classz){ this.classz = classz; }


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
            throw new RepositoryException(String.format("Error al obtener '%s' con ID='%s'", classz.getSimpleName(), id), e);
        }
    }

    @Override
    public List<T> getList() throws RepositoryException {
        try{
            LOGGER.info(String.format("Buscando listado de '%s' ...", classz.getSimpleName()));
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(classz);
            Root<T> root = cq.from(classz);
            return entityManager.createQuery(cq).getResultList();
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new RepositoryException(String.format("Error al buscar listado de entidades '%s'", classz.getSimpleName()), e);
        }
    }

    @Override
    @Transactional
    public T save(T entity) throws RepositoryException {
        try {
            LOGGER.info(String.format("Guardando '%s' ...", classz.getSimpleName()));
            entityManager.persist(entity);
            return entity;
        } catch (PersistenceException e) {
            throw new RepositoryException(String.format("Error al guardar entidad '%s'", classz.getSimpleName()), e);
        }
    }

    @Override
    @Transactional
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
