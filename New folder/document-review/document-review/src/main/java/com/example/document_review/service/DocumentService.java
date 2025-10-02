package com.example.document_review.service;

import com.example.document_review.exception.DocumentServiceException;
import org.springframework.core.io.Resource;
import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    void save(DocumentDto document) throws DocumentServiceException;
    List<DocumentDto> findAllDocuments() throws Exception, DocumentServiceException;
    List<Document> findAll() throws DocumentServiceException;
    DocumentDto findById(Integer id) throws DocumentServiceException;
    DocumentDto deleteById(Integer id) throws DocumentServiceException;
    void changeStatus(Integer id, boolean status) throws DocumentServiceException;
    void uploadDocument(MultipartFile file, DocumentDto documentDto) throws DocumentServiceException;
    List<DocumentDto> getByPartId(Integer id) throws DocumentServiceException;;
    Resource getDocumentByName(String filename) throws IOException, DocumentServiceException;
}
