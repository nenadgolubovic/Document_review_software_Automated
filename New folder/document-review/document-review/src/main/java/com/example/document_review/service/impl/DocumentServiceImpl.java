package com.example.document_review.service.impl;

import com.example.document_review.dto.AssetDto;
import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Document;
import com.example.document_review.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DocumentServiceImpl implements DocumentService {

    @Override
    public void save(DocumentDto document) {

    }

    @Override
    public List<DocumentDto> findAllDocuments() {
        return List.of();
    }

    @Override
    public List<Document> findAll() {
        return List.of();
    }

    @Override
    public DocumentDto findById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void changeStatus(long id, boolean status) {

    }

    @Override
    public DocumentDto assignAsset(AssetDto assetDto, DocumentDto documentDto) {
        return null;
    }

    @Override
    public DocumentDto reassignAsset(AssetDto assetDto, DocumentDto documentDto) {
        return null;
    }
}
