package com.example.document_review.repository.impl;

import com.example.document_review.entity.User;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements MyRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(Object entity) {

    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public List findAll() throws Exception {
        return List.of();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Object entity) {

    }

    public User findByUsername(String username) {
        return em.createQuery(
                        "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}
