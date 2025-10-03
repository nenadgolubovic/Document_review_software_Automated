package com.example.document_review.controller;
import com.example.document_review.dto.DocumentDto;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.service.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/document")
@RestController()
public class DocumentController {

    private final DocumentService documentService;
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument( @RequestPart("partId") String partIdStr,@RequestPart("document") MultipartFile document)
    {
        try {
            int partId = Integer.parseInt(partIdStr);

            DocumentDto documentDto = new DocumentDto();
            documentDto.setPartId(partId);
            documentDto.setDocumentName(document.getOriginalFilename());
            documentDto.setDocumentRoute(document.getOriginalFilename());
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

    @GetMapping("/document/{filename}")
    public ResponseEntity<Resource> getDocument(@PathVariable String filename) throws IOException {

        Resource resource = documentService.getDocumentByName(filename);

        if(resource==null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/get/part/{id}")
    public ResponseEntity<List<DocumentDto>> getByPartId(@PathVariable Integer id) throws Exception {
        List<DocumentDto> documents = documentService.getByPartId(id);
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
    public ResponseEntity<DocumentDto> deleteById(@PathVariable Integer id) throws EntityNotFoundException{
        documentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseStatusException handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
