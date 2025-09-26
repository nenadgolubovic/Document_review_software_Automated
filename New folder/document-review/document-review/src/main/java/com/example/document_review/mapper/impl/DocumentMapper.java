package com.example.document_review.mapper.impl;


import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Comment;
import com.example.document_review.entity.Document;
import com.example.document_review.entity.Part;
import com.example.document_review.entity.User;
import com.example.document_review.mapper.Mapper;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentMapper implements Mapper<DocumentDto, Document> {

    @Override
    public Document toEntity(DocumentDto documentDto) {
        Document document = new Document();
        document.setDocumentName(documentDto.getDocumentName());
        document.setPart(documentDto.getPart());
        document.setDocumentRoute(documentDto.getDocumentRoute());
        document.setComments(documentDto.getComments());
        document.setPartId(documentDto.getPartId());
        document.setDocumentDate(documentDto.getDocumentDate());

        return document;

    }

    @Override
    public DocumentDto toDto(Document document) {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setDocumentName(document.getDocumentName());
        documentDto.setDocumentRoute(document.getDocumentRoute());
        documentDto.setPart(document.getPart());
        documentDto.setComments(document.getComments());


        return documentDto;
    }
}
