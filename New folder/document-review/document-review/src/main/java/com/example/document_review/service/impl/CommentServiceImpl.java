package com.example.document_review.service.impl;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.entity.Comment;
import com.example.document_review.mapper.impl.CommentMapper;
import com.example.document_review.repository.impl.CommentRepository;
import com.example.document_review.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public void save(CommentDto commentDto) {
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
    public void rateComment(CommentDto commentDto, Integer rate) {
        if (commentDto.getRate() == null) {
            commentDto.setRate(rate);
        }
    }


}
