package com.example.document_review.repository.impl;

import com.example.document_review.entity.Comment;
import com.example.document_review.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
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

        return entityManager.find(Comment.class, id);
    }

    @Override
    public List<Comment> findAll() throws EntityNotFoundException {
        List<Comment> comments = entityManager.createQuery("select c from Comment c", Comment.class)
                .getResultList();
        if (comments.isEmpty()) {
            throw new EntityNotFoundException("Comment not found");
        }
        return comments;
    }

    @Override
    public void delete(Integer id) {
        // This method is intentionally left empty for now.
    }


    @Override
    public void update(Comment comment) {
        if (comment == null || comment.getCommentId() == null) {
            throw new IllegalArgumentException("Comment or Comment ID must not be null");
        }
        Comment existingComment = entityManager.find(Comment.class, comment.getCommentId());
        if (existingComment == null) {
            throw new EntityNotFoundException("Comment with ID " + comment.getCommentId() + " not found");
        }
        existingComment.setComment(comment.getComment());
        existingComment.setCommentTitle(comment.getCommentTitle());
        existingComment.setUserId(comment.getUserId());
        existingComment.setCommentDate(comment.getCommentDate());
        existingComment.setApproved(comment.isApproved());
        existingComment.setRate(comment.getRate());
    }

    public List<Comment> getAllByDocumentId(Integer documentId) {
        return entityManager
                .createQuery("select c from Comment c where c.documentId = :documentId", Comment.class)
                .setParameter("documentId", documentId)
                .getResultList();
    }
}
