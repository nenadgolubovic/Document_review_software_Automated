package com.example.document_review.repository;


import java.util.List;


public interface MyRepository <E, Id> {
    void save(E entity);
    E findById(Integer id);
    List<E> findAll() throws Exception;
    void delete(Integer id) throws Exception;
    void update(E entity);
}
