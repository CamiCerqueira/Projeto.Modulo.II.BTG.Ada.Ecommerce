package br.ada.customer.crud.controller.common;

import java.util.List;

public interface CrudRepository<T, ID> {

    void save(T object) throws RepositoryException;

    List<T> listAll() throws RepositoryException;

    T findById(ID id) throws RepositoryException;

    void update(T object) throws RepositoryException;

    void delete(T object) throws RepositoryException;

}
