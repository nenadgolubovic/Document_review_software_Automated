package com.example.document_review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
public class CommentDto {
    private Integer commentId;
    private String comment;
    private Integer userId;
    private String commentTitle;
    private LocalDateTime commentDate;
    private boolean isApproved;
    private Integer rate;
    private Integer documentId;

    public CommentDto(Integer commentId, String comment, Integer userId, String commentTitle, LocalDateTime commentDate, boolean isApproved, Integer rate, Integer documentId) {
        this.commentId = commentId;
        this.comment = comment;
        this.userId = userId;
        this.commentTitle = commentTitle;
        this.commentDate = commentDate;
        this.isApproved = isApproved;
        this.rate = rate;
        this.documentId = documentId;
    }

    public CommentDto() {}

    @Override
    public String toString() {
        return "CommentDto{" +
                "commentId=" + commentId +
                ", comment='" + comment + '\'' +
                ", userId=" + userId +
                ", commentTitle='" + commentTitle + '\'' +
                ", commentDate=" + commentDate +
                ", isApproved=" + isApproved +
                ", rate=" + rate +
                ", documentId=" + documentId +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return isApproved == that.isApproved && Objects.equals(commentId, that.commentId) && Objects.equals(comment, that.comment) && Objects.equals(userId, that.userId) && Objects.equals(commentTitle, that.commentTitle) && Objects.equals(commentDate, that.commentDate) && Objects.equals(rate, that.rate) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, comment, userId, commentTitle, commentDate, isApproved, rate, documentId);
    }


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
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

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

}
