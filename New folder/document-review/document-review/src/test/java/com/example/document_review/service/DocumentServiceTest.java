package com.example.document_review.service;


import com.example.document_review.dto.DocumentDto;
import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.entity.Document;
import com.example.document_review.entity.enums.PartType;
import com.example.document_review.entity.FanBlade;
import com.example.document_review.mapper.impl.DocumentMapper;
import com.example.document_review.mapper.impl.PartMapper;
import com.example.document_review.repository.impl.DocumentRepository;
import com.example.document_review.service.impl.DocumentServiceImpl;
import com.example.document_review.service.impl.PartServiceImpl;
import com.example.document_review.validator.impl.DocumentFileValidator;
import com.example.document_review.validator.impl.DocumentSaveValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {


    @Mock
    private PartServiceImpl partServiceImpl;

    @Mock
    private PartMapper partMapper;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentSaveValidator documentSaveValidator;

    @Mock
    private DocumentFileValidator documentFileValidator;

    @Mock
    private DocumentMapper documentMapper;

    @InjectMocks
    private DocumentServiceImpl documentServiceImpl; // koristi mokove



    @Test
    void uploadDocumentShouldSaveDocument() throws IOException {

        FanBladeDto fanBladeDto = FanBladeDto.builder()
                .partNumber("PartNumberTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();

        DocumentDto documentDto = DocumentDto.builder()
                .documentName("DocumentNameTest")
                .partId(1)
                .documentRoute("DocumentRouteTest.pdf")
                .documentDate(LocalDate.now())
                .build();

        MultipartFile fakeFile = new MockMultipartFile(
                "document",
                "test.pdf",
                "application/pdf",
                "Dummy PDF content".getBytes()
        );


        when(partServiceImpl.getById(documentDto.getPartId())).thenReturn(fanBladeDto);
        when(partMapper.toEntity(fanBladeDto)).thenReturn(mock(FanBlade.class));
        when(documentMapper.toEntity(documentDto)).thenReturn(mock(Document.class));


        documentServiceImpl.uploadDocument(fakeFile, documentDto);


        assertNotNull(documentDto.getDocumentName());
        assertNotNull(documentDto.getDocumentRoute());
        assertEquals(LocalDate.now(), documentDto.getDocumentDate());


        verify(partServiceImpl, times(1)).getById(documentDto.getPartId());
        verify(partMapper, times(1)).toEntity(fanBladeDto);
        verify(documentSaveValidator, times(1)).validate(documentDto);
        verify(documentFileValidator, times(1)).validate(fakeFile);
        verify(documentMapper, times(1)).toEntity(documentDto);
        verify(documentRepository, times(1)).save(any(Document.class));

    }

    @Test
    void uploadDocumentShouldThrowExceptionWhenFileIsEmpty() {
        MultipartFile multipartFile = mock(MultipartFile.class);
        DocumentDto documentDto = new DocumentDto();
        documentDto.setPartId(1);

        when(multipartFile.isEmpty()).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                documentServiceImpl.uploadDocument(multipartFile, documentDto)
        );

        assertEquals("File name is invalid", exception.getMessage());
    }

    @Test
    public void documentServiceGetByIdDocumentDtos(){
        FanBlade fanBlade = FanBlade.builder()
                .partNumber("PartNumberTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();


        Document document1 = Document.builder()
                .documentName("DocumentNameTest")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest.pdf")
                .documentDate(LocalDate.now())
                .build();

        Document document2 = Document.builder()
                .documentName("DocumentNameTest2")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest2.pdf")
                .documentDate(LocalDate.now())
                .build();
        DocumentDto documentDto1 = DocumentDto.builder()
                .documentName("DocumentNameTest1")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest1.pdf")
                .documentDate(LocalDate.now())
                .build();

        DocumentDto documentDto2 = DocumentDto.builder()
                .documentName("DocumentNameTest2")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest2.pdf")
                .documentDate(LocalDate.now())
                .build();


        List<Document> documents = Arrays.asList(document1, document2);

        when(documentRepository.getByPartId(1)).thenReturn(documents);
        when(documentMapper.toDto(document1)).thenReturn(documentDto1);
        when(documentMapper.toDto(document2)).thenReturn(documentDto2);


        List<DocumentDto> result = documentServiceImpl.getByPartId(1);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(documentDto1, result.get(0));
        assertEquals(documentDto2, result.get(1));
        verify(documentRepository, times(1)).getByPartId(1);
        verify(documentMapper, times(1)).toDto(document1);
        verify(documentMapper, times(1)).toDto(document2);

    }

    @Test
    public void documentServiceImplGetDocumentsByNameResource() throws IOException {
        String filename = "test.pdf";

        Path path = Paths.get("C:/Users/Nikola/.../document/" + filename);
        Resource resource = new UrlResource(path.toUri());

        Resource result = documentServiceImpl.getDocumentByName(filename);

        if (!resource.exists() || !resource.isReadable()) {
            assertNull(result);
        } else {
            assertNotNull(result);
            assertEquals(resource.getURI(), result.getURI());
        }
    }
    @Test
    void documentServiceImplGetDocumentByNameFileDoesNotExist() throws IOException {
        String filename = "nonexistent.pdf";

        Resource result = documentServiceImpl.getDocumentByName(filename);

        assertNull(result);
    }

    @Test
    public void documentServiceFindAllDocumentsDocumentDtos() throws Exception {

        FanBlade fanBlade = FanBlade.builder()
                .partNumber("PartNumberTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest").build();

        Document document1 = Document.builder()
                .documentName("DocumentNameTest1")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest1.pdf")
                .documentDate(LocalDate.now())
                .build();
        Document document2 = Document.builder()
                .documentName("DocumentNameTest2")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest2.pdf")
                .documentDate(LocalDate.now())
                .build();

        DocumentDto documentDto1 = DocumentDto.builder()
                .documentName("DocumentNameTest1")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest1.pdf")
                .documentDate(LocalDate.now())
                .build();
        DocumentDto documentDto2 = DocumentDto.builder()
                .documentName("DocumentNameTest2")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest2.pdf")
                .documentDate(LocalDate.now())
                .build();

        List<Document> documents = Arrays.asList(document1, document2);
        when(documentRepository.findAll()).thenReturn(documents);
        when(documentMapper.toDto(document1)).thenReturn(documentDto1);
        when(documentMapper.toDto(document2)).thenReturn(documentDto2);
        List<DocumentDto> result = documentServiceImpl.findAllDocuments();

        verify(documentRepository).findAll();
        verify(documentMapper).toDto(document1);
        verify(documentMapper).toDto(document2);

        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(documentDto1, documentDto2);
    }

    @Test
    public void documentServiceDeleteByIdDocumentDto(){
        Integer documentId = 1;
        DocumentDto result = documentServiceImpl.deleteById(documentId);

        verify(documentRepository).delete(documentId);

        assertThat(result).isNull();
    }


}
