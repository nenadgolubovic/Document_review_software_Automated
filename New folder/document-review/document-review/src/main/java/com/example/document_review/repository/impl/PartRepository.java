package com.example.document_review.repository.impl;

import com.example.document_review.entity.Part;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import com.example.document_review.exception.EntityNotFoundException;
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
        return entityManager.find(Part.class, id);
    }

    @Override
    public List<Part> findAll() throws EntityNotFoundException {
        return entityManager.createQuery("select bp from Part bp", Part.class)
                .getResultList();
    }

    @Override
    public void delete(Integer id) throws EntityNotFoundException {
        Part part = entityManager.find(Part.class, id);

        if (part != null) {
            entityManager.remove(part);
        } else {
            throw new EntityNotFoundException("Part not found");
        }
    }

    @Override
    public void update(Part entity) {
        // This method is intentionally left empty for now.
    }
}
