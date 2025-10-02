package com.example.document_review.repository.impl;

import com.example.document_review.entity.BasicPart;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BasicPartRepository implements MyRepository<BasicPart, Integer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(BasicPart entity) {
        entityManager.persist(entity);
    }


    @Override
    public BasicPart findById(Integer id) {
        return entityManager.find(BasicPart.class, id);
    }

    @Override
    public List<BasicPart> findAll() throws EntityNotFoundException {
    List<BasicPart> basicParts = entityManager.createQuery("select bp from BasicPart bp", BasicPart.class)
            .getResultList();
        if (basicParts.isEmpty()) {
            throw new EntityNotFoundException("Comment not found");
        }
        return basicParts;
    }

    @Override
    public void delete(Integer id) throws Exception {
        BasicPart basicPart = entityManager.find(BasicPart.class, id);

        if (basicPart != null) {
            entityManager.remove(basicPart);
        } else {
            throw new EntityNotFoundException("Basic Part not found");
        }
    }

    @Override
    public void update(BasicPart entity) {
        // This method is intentionally left empty for now.
    }

}


