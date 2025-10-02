package com.example.document_review.repository.impl;

import com.example.document_review.entity.User;
import com.example.document_review.exception.RepositoryOperationException;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository implements MyRepository <User, Integer>{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void save(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List findAll() throws RepositoryOperationException {
        return List.of();
    }

    @Override
    public void delete(Integer id) {
        // This method is intentionally left empty for now.
    }

    @Override
    public void update(User entity) {
        // This method is intentionally left empty for now.
    }


    public User findByUsername(String username) {
        List<User> users = entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

}
