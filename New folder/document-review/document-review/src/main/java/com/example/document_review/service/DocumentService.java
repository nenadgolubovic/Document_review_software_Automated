package com.example.document_review.service;

import com.example.document_review.dto.AssetDto;
import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Document;

import java.util.List;

public interface DocumentService {
    void save(DocumentDto document);
    List<DocumentDto> findAllDocuments();
    DocumentDto findById(long id);
    void deleteById(long id);
    void changeStatus(long id, boolean status);
    DocumentDto assignAsset(AssetDto assetDto, DocumentDto documentDto);
    DocumentDto reassignAsset(AssetDto assetDto, DocumentDto documentDto);
}
