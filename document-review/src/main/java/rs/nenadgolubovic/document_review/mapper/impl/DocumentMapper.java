package rs.nenadgolubovic.document_review.mapper.impl;

import rs.nenadgolubovic.document_review.dto.DocumentDto;
import rs.nenadgolubovic.document_review.entity.Document;
import rs.nenadgolubovic.document_review.mapper.Mapper;

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
