package com.example.document_review.controller;


import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Document;
import com.example.document_review.mapper.impl.DocumentMapper;
import com.example.document_review.repository.impl.DocumentRepository;
import com.example.document_review.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@RequestMapping("/document")
@RestController()
public class DocumentController {

    private DocumentService documentService;
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestPart("document") MultipartFile document)
                                                 {
        try {
            DocumentDto documentDto = new DocumentDto();
            documentService.uploadDocument(document, documentDto);
            return ResponseEntity.ok("File uploaded successfully: " + document.getOriginalFilename());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }



    @GetMapping("/getAll")
    public ResponseEntity<List<DocumentDto>> getAll() throws Exception {
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
