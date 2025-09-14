package mapper.impl;

import dto.DocumentDto;
import mapper.Mapper;
import model.Document;

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
