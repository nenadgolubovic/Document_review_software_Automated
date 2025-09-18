package com.example.document_review.repository.impl;


import com.example.document_review.entity.Engine;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EngineRepository implements MyRepository <Engine, Integer>{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Engine entity) {
        entityManager.persist(entity);
    }

    @Override
    public Engine findById(Integer id) {
        return null;
    }

    @Override
    public List<Engine> findAll() throws Exception {
        return List.of();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Engine entity) {

    }
}
