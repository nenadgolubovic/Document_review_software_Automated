package com.example.document_review.repository.impl;

import com.example.document_review.entity.Comment;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository implements MyRepository<Comment, Integer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Comment entity) {
        entityManager.persist(entity);
    }

    @Override
    public Comment findById(Integer id) {
        return null;
    }

    @Override
    public List<Comment> findAll() throws Exception {
        List<Comment> comments = entityManager.createQuery("select c from Comment c", Comment.class)
                .getResultList();
        if (comments.isEmpty()) {
            throw new Exception("Comment not found");
        }
        return comments;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Comment entity) {

    }
}
