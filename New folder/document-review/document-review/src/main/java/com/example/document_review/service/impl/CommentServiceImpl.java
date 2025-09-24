package com.example.document_review.service.impl;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.entity.Comment;
import com.example.document_review.mapper.impl.CommentMapper;
import com.example.document_review.repository.impl.CommentRepository;
import com.example.document_review.service.CommentService;
import com.example.document_review.validator.impl.CommentSaveValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final CommentSaveValidator commentSaveValidator;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository, CommentSaveValidator commentSaveValidator) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.commentSaveValidator = commentSaveValidator;
    }

    @Override
    @Transactional
    public void save(CommentDto commentDto) {
        commentSaveValidator.validate(commentDto);
        commentRepository.save(commentMapper.toEntity(commentDto));
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
    public void approveComment(Integer id)
    {

        Comment comment = commentRepository.findById(id);
        if (!comment.isApproved()) {
            comment.setApproved(true);
        }
        commentRepository.update(comment);
    }

    @Override
    @Transactional
    public void rejectComment(Integer id) {
        Comment comment = commentRepository.findById(id);
        if (comment.isApproved()) {
            comment.setApproved(false);
        }
        commentRepository.update(comment);
    }

    @Override
    @Transactional
    public void rateComment(Integer commentId, Integer rate) {
        Comment comment = commentRepository.findById(commentId);
        comment.setRate(rate);

        }
    }

