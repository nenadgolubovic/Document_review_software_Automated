package com.example.document_review.repository.impl;

import com.example.document_review.entity.User;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository implements MyRepository <User, Integer>{

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void save(User entity) {
        em.persist(entity);
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public List findAll() throws Exception {
        return List.of();
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(User entity) {

    }


    public User findByUsername(String username) {
        List<User> users = em.createQuery(
                        "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

}
