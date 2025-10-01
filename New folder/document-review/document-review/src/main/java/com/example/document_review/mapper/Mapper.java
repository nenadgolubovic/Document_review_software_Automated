package com.example.document_review.mapper;


public interface Mapper<Dto,Entity> {
    Entity toEntity(Dto dto);
    Dto toDto(Entity entity);
}
