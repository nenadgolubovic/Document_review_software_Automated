package com.example.document_review.service.impl;

import com.example.document_review.dto.AssetDto;
import com.example.document_review.dto.DocumentDto;
import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.Document;
import com.example.document_review.mapper.impl.DocumentMapper;
import com.example.document_review.repository.impl.DocumentRepository;
import com.example.document_review.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentMapper documentMapper, DocumentRepository documentRepository) {
        this.documentMapper = documentMapper;
        this.documentRepository = documentRepository;
    }

    @Override
    @Transactional
    public void uploadDocument(MultipartFile document, DocumentDto documentDto) {

        if (document == null || document.isEmpty()) {
            throw new IllegalArgumentException("File name is invalid");
        }
        try {
            String originalFileName = document.getOriginalFilename();
            String projectDirectory = new File(".").getCanonicalPath();
            Path uploadPath = Paths.get(projectDirectory, "src", "main", "resources", "static", "document");
            Files.createDirectories(uploadPath);
            String fileName = originalFileName;
            Path filePath = uploadPath.resolve(fileName);
            int count = 1;
            String nameWithoutExt = fileName;
            String extension = "";
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex != -1) {
                nameWithoutExt = fileName.substring(0, dotIndex);
                extension = fileName.substring(dotIndex);
            }
            while (Files.exists(filePath)) {
                fileName = nameWithoutExt + "_" + count + extension;
                filePath = uploadPath.resolve(fileName);
                count++;
            }
            Files.write(filePath, document.getBytes());
            documentDto.setDocumentRoute(filePath.toString());
            documentDto.setDocumentName(fileName);
            documentDto.setDocumentDate(LocalDate.now());

            documentRepository.save(documentMapper.toEntity(documentDto));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void save(DocumentDto document) {

    }

    @Override
    public List<DocumentDto> findAllDocuments() throws Exception {
        return documentRepository.findAll().stream().map(entity -> documentMapper.toDto(entity)).collect(Collectors.toList());
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
