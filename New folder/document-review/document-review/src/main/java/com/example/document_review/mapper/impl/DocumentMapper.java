package com.example.document_review.mapper.impl;


import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Document;
import com.example.document_review.entity.User;
import com.example.document_review.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper implements Mapper<DocumentDto, Document> {

    @Override
    public Document toEntity(DocumentDto documentDto) {
        Document document = new Document();
        document.setDocumentName(documentDto.getDocumentName());
        document.setDocumentType(documentDto.getDocumentType());
        document.setDocumentDate(documentDto.getDocumentDate());
        document.setAssetId(documentDto.getAssetId());
        document.setDocumentRoute(documentDto.getDocumentRoute());

        return document;

    }

    @Override
    public DocumentDto toDto(Document document) {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setDocumentName(document.getDocumentName());
        documentDto.setDocumentName(document.getDocumentName());
        documentDto.setDocumentType(document.getDocumentType());
        documentDto.setDocumentDate(document.getDocumentDate());
        documentDto.setAssetId(document.getAssetId());
        documentDto.setDocumentRoute(document.getDocumentRoute());

        return documentDto;
    }
}
