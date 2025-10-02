package com.example.document_review.repository;


import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.exception.RepositoryOperationException;

import java.util.List;


public interface MyRepository <E, I> {
    void save(E entity) throws RepositoryOperationException;
    E findById(I id) throws EntityNotFoundException;
    List<E> findAll() throws Exception;
    void delete(I id) throws RepositoryOperationException;
    void update(E entity) throws RepositoryOperationException;
}
