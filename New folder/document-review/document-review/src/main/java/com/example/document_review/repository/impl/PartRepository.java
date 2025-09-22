package com.example.document_review.repository.impl;

import com.example.document_review.entity.Part;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PartRepository implements MyRepository <Part, Integer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Part entity) {
        entityManager.persist(entity);
    }

    @Override
    public Part findById(Integer id) {
        return null;
    }

    @Override
    public List<Part> findAll() throws Exception {
        return List.of();
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Part entity) {

    }
}
