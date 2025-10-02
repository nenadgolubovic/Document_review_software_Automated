package com.example.document_review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Entity
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentText;
    private Integer userId;
    @Column(name = "document_id", nullable = false)
    private Integer documentId;
    private String commentTitle;
    private LocalDateTime commentDate;
    private boolean isApproved;
    private Integer rate;



    public Comment() {

    }


    public Integer getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Integer documentId)
    {
        this.documentId = documentId;
    }
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return commentText;
    }

    public void setComment(String comment) {
        this.commentText = comment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }


    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return isApproved == comment1.isApproved && Objects.equals(commentId, comment1.commentId) && Objects.equals(commentText, comment1.commentText) && Objects.equals(userId, comment1.userId) && Objects.equals(commentTitle, comment1.commentTitle) && Objects.equals(commentDate, comment1.commentDate) && Objects.equals(rate, comment1.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentText, userId, documentId, commentTitle, commentDate, isApproved, rate);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comment='" + commentText + '\'' +
                ", userId=" + userId +
                ", documentId=" + documentId +
                ", commentTitle='" + commentTitle + '\'' +
                ", commentDate=" + commentDate +
                ", isApproved=" + isApproved +
                ", rate=" + rate +
                '}';
    }

}
