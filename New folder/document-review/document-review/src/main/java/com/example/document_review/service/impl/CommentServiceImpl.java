package com.example.document_review.service.impl;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.entity.Comment;
import com.example.document_review.entity.Document;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.mapper.impl.CommentMapper;
import com.example.document_review.mapper.impl.DocumentMapper;
import com.example.document_review.repository.impl.CommentRepository;
import com.example.document_review.repository.impl.DocumentRepository;
import com.example.document_review.service.CommentService;
import com.example.document_review.validator.impl.CommentSaveValidator;
import com.example.document_review.validator.impl.DocumentFileValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final CommentSaveValidator commentSaveValidator;
    private final DocumentRepository documentRepository;
    private final DocumentServiceImpl documentServiceImpl;
    private final DocumentMapper documentMapper;
    private final DocumentFileValidator documentFileValidator;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository, DocumentRepository documentRepository, CommentSaveValidator commentSaveValidator, DocumentServiceImpl documentServiceImpl, DocumentMapper documentMapper, DocumentFileValidator documentFileValidator) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.commentSaveValidator = commentSaveValidator;
        this.documentServiceImpl = documentServiceImpl;
        this.documentMapper = documentMapper;
        this.documentRepository = documentRepository;
        this.documentFileValidator = documentFileValidator;
    }

    @Override
    @Transactional
    public void save(CommentDto commentDto) throws ValidationException {

        Document document = documentRepository.findById(commentDto.getDocumentId());
        documentFileValidator.validateDocument(document);
        commentSaveValidator.validate(commentDto);
        Comment comment = commentMapper.toEntity(commentDto);
//        comment.setDocument(document);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAll() throws Exception {
        return commentRepository.findAll().stream().map(entity -> commentMapper.toDto(entity)).collect(Collectors.toList());
    }


    @Override
    public CommentDto findById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void update(CommentDto commentDto) {

    }

    @Override
    @Transactional
    public void approveComment(Integer id) throws Exception
    {

        Comment comment = commentRepository.findById(id);
        if (!comment.isApproved()) {
            comment.setApproved(true);
        }
        commentRepository.update(comment);
    }

    @Override
    @Transactional
    public void rejectComment(Integer id) throws Exception{
        try{
            Comment comment = commentRepository.findById(id);
            if (comment.isApproved()) {
                comment.setApproved(false);
            }
            commentRepository.update(comment);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }


    }

    @Override
    @Transactional
    public void rateComment(Integer commentId, Integer rate) throws Exception{
        Comment comment = commentRepository.findById(commentId);
        comment.setRate(rate);

        }

    @Override
    public List<CommentDto> getAllByDocumentIdAndUserId(Integer documentId, Integer userId) {
        return commentRepository.getAllByDocumentIdAndUserId(documentId, userId).stream().map(entity -> commentMapper.toDto(entity)).collect(Collectors.toList());
    }
}

