package com.example.document_review.repository.impl;

import com.example.document_review.entity.Asset;
import com.example.document_review.repository.AssetSpringDataRepository;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssetRepository implements MyRepository <Asset, Integer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Asset entity) {
        entityManager.persist(entity);
    }

    @Override
    public Asset findById(Integer id) {
        List<Asset> assets = entityManager.createQuery(
                "SELECT a FROM Asset a WHERE a.id = :id", Asset.class
        ).setParameter("id", id).getResultList();
        return assets.isEmpty() ? null : assets.get(0);
    }

    @Override
    public List<Asset> findAll() throws Exception {
        List<Asset> assets = entityManager.createQuery("SELECT a FROM Asset a", Asset.class).getResultList();
        if(assets.isEmpty()){
            throw new Exception("Departments dont exist!");
        }
        return assets;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Asset entity) {

    }
}
