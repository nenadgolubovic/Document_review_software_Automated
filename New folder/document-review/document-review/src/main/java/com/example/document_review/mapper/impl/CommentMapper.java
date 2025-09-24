package com.example.document_review.mapper.impl;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.entity.Comment;
import com.example.document_review.mapper.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@org.springframework.stereotype.Component
public class CommentMapper implements Mapper<CommentDto, Comment> {

    @Override
    public Comment toEntity(CommentDto commentDto) {
        return new Comment(null,
                commentDto.getCommentTitle(),
                commentDto.getUserId(),
                commentDto.getDocument(),
                commentDto.getComment(),
                commentDto.getCommentDate(),
                commentDto.isApproved(),
                commentDto.getRate());
    }

    @Override
    public CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getCommentId(),
                comment.getCommentTitle(),
                comment.getUserId(),
                comment.getComment(),
                comment.getCommentDate(),
                comment.isApproved(),
                comment.getRate(),
                comment.getDocument());
    }
}
