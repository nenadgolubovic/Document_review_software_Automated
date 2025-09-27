package com.example.document_review.validator.impl;
import com.example.document_review.entity.Document;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class DocumentFileValidator implements Validator<MultipartFile> {
    @Override
    public void validate(MultipartFile documentFile) throws ValidationException {
        if (documentFile == null || documentFile.isEmpty()) {
            throw new ValidationException("File is missing or empty");
        }

        String originalFileName = documentFile.getOriginalFilename();
        if (originalFileName == null || originalFileName.trim().isEmpty()) {
            throw new ValidationException("File name is invalid");
        }

        if (!originalFileName.endsWith(".pdf") && !originalFileName.endsWith(".docx")) {
            throw new ValidationException("Unsupported file type. Only PDF or DOCX allowed");
        }
    }

    public void validateDocument(Document document) {

        if(document == null){
            throw new ValidationException("Not assigned Document to comment");
        }

    }
}
