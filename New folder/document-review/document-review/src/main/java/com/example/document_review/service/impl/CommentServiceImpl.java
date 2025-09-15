package com.example.document_review.service.impl;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    @Override
    public void save(CommentDto commentDto) {

    }

    @Override
    public List<CommentDto> findAll() {
        return List.of();
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
    public void approveComment(CommentDto commentDto) {
        if (!commentDto.isApproved()) {
            commentDto.setApproved(true);
        }
    }

    @Override
    public void rejectComment(CommentDto commentDto) {
        if (commentDto.isApproved()) {
            commentDto.setApproved(false);
        }

    }

    @Override
    public void rateComment(CommentDto commentDto, Integer rate) {
        if (commentDto.getRate() == null) {
            commentDto.setRate(rate);
        }
    }

}
