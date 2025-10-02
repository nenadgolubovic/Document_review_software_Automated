package com.example.document_review.mapper;


public interface Mapper<D,E> {
    E toEntity(D dto);
    D toDto(E entity);
}
