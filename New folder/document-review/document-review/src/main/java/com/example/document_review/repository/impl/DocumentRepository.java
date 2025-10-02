package com.example.document_review.repository.impl;

import com.example.document_review.entity.Document;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentRepository implements MyRepository <Document, Integer>{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Document entity) {
        entityManager.persist(entity);
    }

    @Override
    public Document findById(Integer id) {
        return entityManager.find(Document.class, id);


    }

    @Override
    public List<Document> findAll() throws Exception {
        List<Document> documents = entityManager.createQuery("select d from Document d", Document.class)
                .getResultList();
        if (documents.isEmpty()) {
            throw new Exception("Documents not found");
        }
        return documents;
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void update(Document entity) {
        // This method is intentionally left empty for now.
    }

    public List<Document> getByPartId(Integer id) {
        return entityManager.createQuery(
                        "SELECT d FROM Document d WHERE d.partId = :id", Document.class)
                .setParameter("id", id)
                .getResultList();
    }
}
