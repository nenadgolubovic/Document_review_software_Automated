package com.example.document_review.repository.impl;

import com.example.document_review.entity.FanBlade;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FanBladeRepository implements MyRepository<FanBlade, Integer> {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(FanBlade entity) {
        entityManager.persist(entity);
    }


    @Override
    public FanBlade findById(Integer id) {
        return entityManager.find(FanBlade.class, id);
    }

    @Override
    public List<FanBlade> findAll() throws Exception {
        List<FanBlade> fanBlades = entityManager.createQuery("select bp from FanBlade bp", FanBlade.class)
                .getResultList();
        if (fanBlades.isEmpty()) {
            throw new Exception("Comment not found");
        }
        return fanBlades;
    }

    @Override
    public void delete(Integer id) throws Exception {
        FanBlade fanBlade = entityManager.find(FanBlade.class, id);

        if (fanBlade != null) {
            entityManager.remove(fanBlade);
        } else {
            throw new EntityNotFoundException("Basic Part not found");
        }
    }

    @Override
    public void update(FanBlade entity) {
        // This method is intentionally left empty for now.
    }
}
