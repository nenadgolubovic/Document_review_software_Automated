package com.example.document_review.service;

import com.example.document_review.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void save(CommentDto commentDto);
    List<CommentDto> findAll() throws Exception;
    CommentDto findById(long id);
    void deleteById(long id);
    void update(CommentDto commentDto);
    void approveComment(CommentDto commentDto);
    void rejectComment(CommentDto commentDto);
    void rateComment(CommentDto commentDto, Integer rate);

}
