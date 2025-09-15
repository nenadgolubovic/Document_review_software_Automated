package com.example.document_review.mapper;

import javax.swing.text.html.parser.Entity;

public interface Mapper<Dto,Entity> {
    Entity toEntity(Dto dto);
    Dto toDto(Entity entity);
}
