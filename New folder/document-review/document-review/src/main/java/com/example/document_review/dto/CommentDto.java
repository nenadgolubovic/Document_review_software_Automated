package com.example.document_review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class CommentDto {
    private Integer commentId;
    private String commentText;
    private Integer userId;
    private String commentTitle;
    private LocalDateTime commentDate;
    private boolean isApproved;
    private Integer rate;
    private Integer documentId;

    public CommentDto() {}

    @Override
    public String toString() {
        return "CommentDto{" +
                "commentId=" + commentId +
                ", comment='" + commentText + '\'' +
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
        return isApproved == that.isApproved && Objects.equals(commentId, that.commentId) && Objects.equals(commentText, that.commentText) && Objects.equals(userId, that.userId) && Objects.equals(commentTitle, that.commentTitle) && Objects.equals(commentDate, that.commentDate) && Objects.equals(rate, that.rate) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentText, userId, commentTitle, commentDate, isApproved, rate, documentId);
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
