package mapper.impl;

import dto.CommentDto;
import mapper.Mapper;

import java.awt.*;

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
