package com.example.document_review.repository;


import com.example.document_review.entity.Comment;
import com.example.document_review.repository.impl.CommentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentRepositoryTests {

    CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepository();
        ReflectionTestUtils.setField(commentRepository, "entityManager", entityManager);
    }
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void CommentRepository_Save() {

        Comment comment = Comment.builder()
                .commentTitle("Test comment title")
                .comment("Test comment")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(5).build();


        commentRepository.save(comment);

        Comment savedComment = entityManager.find(Comment.class, comment.getCommentId());


        Assertions.assertThat(savedComment).isNotNull();
        Assertions.assertThat(savedComment.getCommentTitle()).isEqualTo(comment.getCommentTitle());
        Assertions.assertThat(savedComment.getComment()).isEqualTo(comment.getComment());
        Assertions.assertThat(savedComment.getCommentDate()).isEqualTo(comment.getCommentDate());
        Assertions.assertThat(savedComment.isApproved()).isEqualTo(comment.isApproved());
        Assertions.assertThat(savedComment.getRate()).isEqualTo(comment.getRate());
        Assertions.assertThat(savedComment.getUserId()).isEqualTo(comment.getUserId());
        Assertions.assertThat(savedComment.getDocumentId()).isEqualTo(comment.getDocumentId());

    }

    @Test
    public void CommentRepository_FindById_ReturnComment() {
        Comment comment = Comment.builder()
                .commentTitle("Test comment title")
                .comment("Test comment")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(5).build();

        commentRepository.save(comment);

        Comment foundComment = commentRepository.findById(comment.getCommentId());

        Assertions.assertThat(foundComment).isNotNull();
        Assertions.assertThat(foundComment.getCommentTitle()).isEqualTo(comment.getCommentTitle());
        Assertions.assertThat(foundComment.getComment()).isEqualTo(comment.getComment());
        Assertions.assertThat(foundComment.getCommentDate()).isEqualTo(comment.getCommentDate());
        Assertions.assertThat(foundComment.isApproved()).isEqualTo(comment.isApproved());
        Assertions.assertThat(foundComment.getRate()).isEqualTo(comment.getRate());
        Assertions.assertThat(foundComment.getUserId()).isEqualTo(comment.getUserId());
        Assertions.assertThat(foundComment.getDocumentId()).isEqualTo(comment.getDocumentId());

    }

    @Test
    public void CommentRepository_FindAll_ReturnAllComments() throws Exception {

        Comment comment1 = Comment.builder()
                .commentTitle("Test comment title 1")
                .comment("Test comment 1")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(5).build();

        Comment comment2 = Comment.builder()
                .commentTitle("Test comment title 2")
                .comment("Test comment 2")
                .commentDate(LocalDateTime.now())
                .documentId(2)
                .userId(1)
                .isApproved(false)
                .rate(1).build();


        commentRepository.save(comment1);
        commentRepository.save(comment2);

        List<Comment> comments = commentRepository.findAll();

        Assertions.assertThat(comments.size()).isEqualTo(2);
        Assertions.assertThat(comments.get(0).getCommentTitle()).isEqualTo(comment1.getCommentTitle());
        Assertions.assertThat(comments.get(0).getComment()).isEqualTo(comment1.getComment());
        Assertions.assertThat(comments.get(0).getCommentDate()).isEqualTo(comment1.getCommentDate());
        Assertions.assertThat(comments.get(0).isApproved()).isEqualTo(comment1.isApproved());
        Assertions.assertThat(comments.get(0).getRate()).isEqualTo(comment1.getRate());
        Assertions.assertThat(comments.get(0).getUserId()).isEqualTo(comment1.getUserId());
        Assertions.assertThat(comments.get(0).getDocumentId()).isEqualTo(comment1.getDocumentId());
        Assertions.assertThat(comments.get(1).getCommentTitle()).isEqualTo(comment2.getCommentTitle());
        Assertions.assertThat(comments.get(1).getComment()).isEqualTo(comment2.getComment());
        Assertions.assertThat(comments.get(1).getCommentDate()).isEqualTo(comment2.getCommentDate());
        Assertions.assertThat(comments.get(1).isApproved()).isEqualTo(comment2.isApproved());
        Assertions.assertThat(comments.get(1).getRate()).isEqualTo(comment2.getRate());
        Assertions.assertThat(comments.get(1).getUserId()).isEqualTo(comment2.getUserId());
        Assertions.assertThat(comments.get(1).getDocumentId()).isEqualTo(comment2.getDocumentId());
    }

    @Test
    public void CommentRepository_Update_ReturnUpdatedComment(){
        Comment comment = Comment.builder()
                .commentTitle("Test comment title 1")
                .comment("Test comment 1")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(5).build();

        commentRepository.save(comment);

        Comment newComment = commentRepository.findById(comment.getCommentId());

        newComment.setComment("Updated comment");
        newComment.setCommentDate(LocalDateTime.now());
        newComment.setCommentTitle("Updated title");
        newComment.setDocumentId(2);
        newComment.setUserId(1);
        newComment.setApproved(false);
        newComment.setRate(3);

        commentRepository.update(newComment);

        Comment changedComment = commentRepository.findById(comment.getCommentId());

        Assertions.assertThat(changedComment).isNotNull();
        Assertions.assertThat(changedComment.getCommentTitle()).isEqualTo(newComment.getCommentTitle());
        Assertions.assertThat(changedComment.getComment()).isEqualTo(newComment.getComment());
        Assertions.assertThat(changedComment.getCommentDate()).isEqualTo(newComment.getCommentDate());
        Assertions.assertThat(changedComment.isApproved()).isEqualTo(newComment.isApproved());
        Assertions.assertThat(changedComment.getRate()).isEqualTo(newComment.getRate());
        Assertions.assertThat(changedComment.getUserId()).isEqualTo(newComment.getUserId());
        Assertions.assertThat(changedComment.getDocumentId()).isEqualTo(newComment.getDocumentId());

    }

    @Test
    public void CommentRepository_GetAllByDocumentId_ReturnedAllCommentsByDocumentId() {
        Comment comment1 = Comment.builder()
                .commentTitle("Test comment title 1")
                .comment("Test comment 1")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(5).build();

        Comment comment2 = Comment.builder()
                .commentTitle("Test comment title 2")
                .comment("Test comment 2")
                .commentDate(LocalDateTime.now())
                .documentId(2)
                .userId(1)
                .isApproved(false)
                .rate(1).build();

        Comment comment3 = Comment.builder()
                .commentTitle("Test comment title 3")
                .comment("Test comment 3")
                .commentDate(LocalDateTime.now())
                .documentId(2)
                .userId(2)
                .isApproved(true)
                .rate(2).build();


        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);

        List<Comment> comments1 = commentRepository.getAllByDocumentId(2);
        List<Comment> comments2 = commentRepository.getAllByDocumentId(1);

        Assertions.assertThat(comments1.size()).isEqualTo(2);
        Assertions.assertThat(comments2.size()).isEqualTo(1);
        Assertions.assertThat(comments1.get(0).getCommentTitle()).isEqualTo(comment2.getCommentTitle());
        Assertions.assertThat(comments1.get(0).getComment()).isEqualTo(comment2.getComment());
        Assertions.assertThat(comments1.get(0).getCommentDate()).isEqualTo(comment2.getCommentDate());
        Assertions.assertThat(comments1.get(0).getDocumentId()).isEqualTo(comment2.getDocumentId());
        Assertions.assertThat(comments1.get(0).getUserId()).isEqualTo(comment2.getUserId());
        Assertions.assertThat(comments1.get(0).isApproved()).isEqualTo(comment2.isApproved());
        Assertions.assertThat(comments1.get(0).getRate()).isEqualTo(comment2.getRate());

        Assertions.assertThat(comments1.get(1).getCommentTitle()).isEqualTo(comment3.getCommentTitle());
        Assertions.assertThat(comments1.get(1).getComment()).isEqualTo(comment3.getComment());
        Assertions.assertThat(comments1.get(1).getCommentDate()).isEqualTo(comment3.getCommentDate());
        Assertions.assertThat(comments1.get(1).getDocumentId()).isEqualTo(comment3.getDocumentId());
        Assertions.assertThat(comments1.get(1).getUserId()).isEqualTo(comment3.getUserId());
        Assertions.assertThat(comments1.get(1).isApproved()).isEqualTo(comment3.isApproved());
        Assertions.assertThat(comments1.get(1).getRate()).isEqualTo(comment3.getRate());

        Assertions.assertThat(comments2.get(0).getCommentTitle()).isEqualTo(comment1.getCommentTitle());
        Assertions.assertThat(comments2.get(0).getComment()).isEqualTo(comment1.getComment());
        Assertions.assertThat(comments2.get(0).getCommentDate()).isEqualTo(comment1.getCommentDate());
        Assertions.assertThat(comments2.get(0).getDocumentId()).isEqualTo(comment1.getDocumentId());
        Assertions.assertThat(comments2.get(0).getUserId()).isEqualTo(comment1.getUserId());
        Assertions.assertThat(comments2.get(0).isApproved()).isEqualTo(comment1.isApproved());
        Assertions.assertThat(comments2.get(0).getRate()).isEqualTo(comment1.getRate());


    }

}
