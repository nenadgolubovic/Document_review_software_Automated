package com.example.document_review.service;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.exception.CommentServiceException;
import com.example.document_review.exception.EntityNotFoundException;

import java.util.List;

public interface CommentService {
    void save(CommentDto commentDto);
    List<CommentDto> findAll() throws EntityNotFoundException;
    CommentDto findById(long id) throws EntityNotFoundException;
    void deleteById(long id) throws EntityNotFoundException;
    void update(CommentDto commentDto) throws EntityNotFoundException;
    void approveComment(Integer id) throws CommentServiceException;
    void rejectComment(Integer id) throws CommentServiceException;
    void rateComment(Integer commentId, Integer rate) throws CommentServiceException;
    List<CommentDto> getAllByDocumentId(Integer documentId);
}
