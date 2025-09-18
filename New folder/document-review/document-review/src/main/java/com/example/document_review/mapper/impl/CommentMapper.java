package com.example.document_review.mapper.impl;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.mapper.Mapper;

import java.awt.*;

@org.springframework.stereotype.Component
public class CommentMapper implements Mapper<CommentDto, Component> {
    @Override
    public Component toEntity(CommentDto componentDto) {
        return null;
    }

    @Override
    public CommentDto toDto(Component component) {
        return null;
    }
}
