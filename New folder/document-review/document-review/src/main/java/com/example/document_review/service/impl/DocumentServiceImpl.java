package com.example.document_review.service.impl;

import com.example.document_review.dto.DocumentDto;
import com.example.document_review.dto.PartDto;
import com.example.document_review.entity.Document;
import com.example.document_review.mapper.impl.DocumentMapper;
import com.example.document_review.mapper.impl.PartMapper;
import com.example.document_review.repository.impl.DocumentRepository;
import com.example.document_review.service.DocumentService;
import com.example.document_review.validator.impl.DocumentFileValidator;
import com.example.document_review.validator.impl.DocumentSaveValidator;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.UrlResource;


@Service
public class DocumentServiceImpl implements DocumentService {

    private final PartServiceImpl partServiceImpl;
    private final PartMapper partMapper;
    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;
    private DocumentSaveValidator documentSaveValidator;
    private DocumentFileValidator documentFileValidator;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper, DocumentSaveValidator documentSaveValidator, DocumentFileValidator documentFileValidator, PartServiceImpl partServiceImpl, PartMapper partMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.documentSaveValidator = documentSaveValidator;
        this.documentFileValidator = documentFileValidator;
        this.partServiceImpl = partServiceImpl;
        this.partMapper = partMapper;
    }




    @Override
    @Transactional
    public void uploadDocument(MultipartFile document, DocumentDto documentDto) {

        PartDto part = partServiceImpl.getById(documentDto.getPartId());
        documentDto.setPart(partMapper.toEntity(part));
        documentSaveValidator.validate(documentDto);
        documentFileValidator.validate(document);

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
    public List<DocumentDto> getByPartId(Integer id) {
        return documentRepository.getByPartId(id).stream().map(entity -> documentMapper.toDto(entity)).collect(Collectors.toList());
    }

    @Override
    public Resource getDocumentByName(String filename) throws IOException{

        Path path = Paths.get("C:/Users/Nikola/.../document/" + filename);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return null;
        }
        return resource;
    }



    @Override
    public List<DocumentDto> findAllDocuments() throws Exception {
        return documentRepository.findAll().stream().map(entity -> documentMapper.toDto(entity)).collect(Collectors.toList());
    }




    @Override
    @Transactional
    public DocumentDto deleteById(Integer id) {
        documentRepository.delete(id);
        return null;
    }

    @Override
    public void changeStatus(Integer id, boolean status) {
        // This method is intentionally left empty for now.
    }
    @Override
    public void save(DocumentDto document) {
        // This method is intentionally left empty for now.
    }
    @Override
    public List<Document> findAll() {
        return List.of();
    }

    @Override
    public DocumentDto findById(Integer id) {
        return documentMapper.toDto(documentRepository.findById(id));
    }

}
