package com.example.document_review.service;

import com.example.document_review.exception.DocumentServiceException;
import com.example.document_review.exception.EntityNotFoundException;
import org.springframework.core.io.Resource;
import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    void save(DocumentDto document) throws DocumentServiceException;
    List<DocumentDto> findAllDocuments() throws EntityNotFoundException;
    List<Document> findAll() throws EntityNotFoundException;
    DocumentDto findById(Integer id) throws EntityNotFoundException;
    DocumentDto deleteById(Integer id) throws EntityNotFoundException;
    void changeStatus(Integer id, boolean status) throws DocumentServiceException;
    void uploadDocument(MultipartFile file, DocumentDto documentDto) throws DocumentServiceException;
    List<DocumentDto> getByPartId(Integer id) throws DocumentServiceException;
    Resource getDocumentByName(String filename) throws IOException, DocumentServiceException;
}
