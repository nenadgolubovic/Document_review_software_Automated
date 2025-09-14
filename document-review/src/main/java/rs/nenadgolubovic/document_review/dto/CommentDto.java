package dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDto {
    private Integer commentId;
    private String comment;
    private Integer userId;
    private String commentTitle;
    private LocalDateTime commentDate;

    public CommentDto(Integer commentId, String comment, Integer userId, String commentTitle, LocalDateTime commentDate) {
        this.commentId = commentId;
        this.comment = comment;
        this.userId = userId;
        this.commentTitle = commentTitle;
        this.commentDate = commentDate;
    }

    public CommentDto() {
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



    @Override
    public String toString() {
        return "CommentDto{" +
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
        CommentDto that = (CommentDto) o;
        return Objects.equals(commentId, that.commentId) && Objects.equals(comment, that.comment) && Objects.equals(userId, that.userId) && Objects.equals(commentTitle, that.commentTitle) && Objects.equals(commentDate, that.commentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, comment, userId, commentTitle, commentDate);
    }
}
