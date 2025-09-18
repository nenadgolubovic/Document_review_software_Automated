package com.example.document_review.controller;


import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Document;
import com.example.document_review.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController("/document")
public class DocumentController {

    private DocumentService documentService;


    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DocumentDto>> getAll() {
        List<DocumentDto> documents = documentService.findAllDocuments();
        return ResponseEntity.ok(documents);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<DocumentDto> getById(@PathVariable Long id) {
        DocumentDto document = documentService.findById(id);
        if (document != null) {
            return ResponseEntity.ok(document);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
