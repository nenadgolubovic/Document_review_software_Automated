package com.example.document_review.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.parser.Entity;
import java.util.List;


public interface MyRepository <E, Id> {
    void save(E entity);
    E findById(Integer id);
    List<E> findAll() throws Exception;
    void delete(String id);
    void update(E entity);
}
