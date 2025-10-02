package com.example.document_review.repository;


import java.util.List;


public interface MyRepository <E, I> {
    void save(E entity);
    E findById(I id);
    List<E> findAll() throws Exception;
    void delete(I id) throws Exception;
    void update(E entity) throws Exception;
}
