package com.example.document_review.controller;


import com.example.document_review.dto.DocumentDto;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
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
    public ResponseEntity<DocumentDto> getById(@PathVariable Integer id) throws Exception {
        DocumentDto documentDto = documentService.findById(id);
        if (documentDto != null) {
            return ResponseEntity.ok(documentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DocumentDto> deleteById(@PathVariable Integer id) {
        documentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseStatusException handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
