package sube.interviews.mareoenvios.repository;

import sube.interviews.mareoenvios.exception.RepositoryException;

import java.util.List;

public interface GenericRepository<T> {

    T getById( Long id ) throws RepositoryException;

    List<T> getList() throws RepositoryException;

    void update( T entity ) throws RepositoryException;
}
