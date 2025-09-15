package rs.nenadgolubovic.document_review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String comment;
    private Integer userId;
    private String commentTitle;
    private LocalDateTime commentDate;

    public Comment(Integer commentId, String comment, Integer userId, String commentTitle, LocalDateTime commentDate) {
        this.commentId = commentId;
        this.comment = comment;
        this.userId = userId;
        this.commentTitle = commentTitle;
        this.commentDate = commentDate;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comment='" + comment + '\'' +
                ", userId=" + userId +
                ", commentTitle='" + commentTitle + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(commentId, comment1.commentId) && Objects.equals(comment, comment1.comment) && Objects.equals(userId, comment1.userId) && Objects.equals(commentTitle, comment1.commentTitle) && Objects.equals(commentDate, comment1.commentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, comment, userId, commentTitle, commentDate);
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
}
