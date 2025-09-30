package com.example.document_review.service;


import com.example.document_review.dto.DocumentDto;
import com.example.document_review.entity.Document;
import com.example.document_review.entity.Enums.PartType;
import com.example.document_review.entity.FanBlade;
import com.example.document_review.mapper.impl.DocumentMapper;
import com.example.document_review.repository.impl.DocumentRepository;
import com.example.document_review.service.impl.DocumentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {


    @Mock
    DocumentRepository documentRepository;

    @Mock
    DocumentMapper documentMapper;

    @InjectMocks
    DocumentServiceImpl documentServiceImpl;


    @Test
    public void DocumentService_UploadDocument(){

    }

    @Test
    public void DocumentService_GetById_DocumentDtos(){

    }

    @Test
    public void DocumentService_GetDocumentsByName_Resource(){

    }

    @Test
    public void DocumentService_findAllDocuments_DocumentDtos() throws Exception {

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
    public void DocumentService_DeleteById_DocumentDto(){
        Integer documentId = 1;
        DocumentDto result = documentServiceImpl.deleteById(documentId);

        verify(documentRepository).delete(documentId);

        assertThat(result).isNull();
    }


}
