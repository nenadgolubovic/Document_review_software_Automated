package com.example.document_review.repository;


import com.example.document_review.exception.RepositoryOperationException;

import java.util.List;


public interface MyRepository <E, I> {
    void save(E entity) throws RepositoryOperationException;
    E findById(I id) throws RepositoryOperationException;
    List<E> findAll() throws RepositoryOperationException;
    void delete(I id) throws RepositoryOperationException;
    void update(E entity) throws RepositoryOperationException;
}
