package com.example.document_review.mapper.impl;


import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Document;
import com.example.document_review.mapper.Mapper;

public class DocumentMapper implements Mapper<DocumentDto, Document> {

    @Override
    public Document toEntity(DocumentDto documentDto) {
        return null;
    }

    @Override
    public DocumentDto toDto(Document document) {
        return null;
    }
}
