package com.example.document_review.validator.impl;

import com.example.document_review.dto.DocumentDto;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class DocumentSaveValidator implements Validator<DocumentDto> {

    @Override
    public void validate(DocumentDto documentDto) throws ValidationException {
        if (documentDto == null) {
            throw new ValidationException("Document data is missing");
        }

        if (documentDto.getPart() == null) {
            throw new ValidationException("Part is not assigned");
        }

    }
}
