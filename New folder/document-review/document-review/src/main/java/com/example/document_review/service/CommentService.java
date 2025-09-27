package com.example.document_review.service;

import com.example.document_review.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void save(CommentDto commentDto);
    List<CommentDto> findAll() throws Exception;
    CommentDto findById(long id);
    void deleteById(long id);
    void update(CommentDto commentDto);
    void approveComment(Integer id) throws Exception;
    void rejectComment(Integer id) throws Exception;
    void rateComment(Integer commentId, Integer rate) throws Exception;
    List<CommentDto> getAllByDocumentIdAndUserId(Integer documentId, Integer userId);
}
