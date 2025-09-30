package com.example.document_review.controller;


import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.dto.DocumentDto;
import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.entity.Document;
import com.example.document_review.entity.Enums.PartType;
import com.example.document_review.service.impl.BasicPartServiceImpl;
import com.example.document_review.service.impl.DocumentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DocumentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private DocumentDto documentDto1;
    private DocumentDto documentDto2;
    private DocumentDto documentDto3;

    @MockitoBean
    private DocumentServiceImpl documentServiceImpl;

    @BeforeEach
    public void init() {
        FanBladeDto fanBladeDto = FanBladeDto.builder()
                .partNumber("PartNumberTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();

        documentDto1 = DocumentDto.builder()
                .documentName("DocumentNameTest")
                .partId(1)
                .documentRoute("DocumentRouteTest.pdf")
                .documentDate(LocalDate.now())
                .build();
        documentDto2 = DocumentDto.builder()
                .documentName("DocumentNameTest2")
                .partId(1)
                .documentRoute("DocumentRouteTest2.pdf")
                .documentDate(LocalDate.now())
                .build();
    }
    @Test
    public void DocumentController_Upload_ShouldCallService() throws Exception {

        MockMultipartFile mockFile = new MockMultipartFile(
                "document",
                "test-document.pdf",
                "application/pdf",
                "Test file content".getBytes()
        );

        MockMultipartFile partIdPart = new MockMultipartFile(
                "partId",
                "",
                "text/plain",
                "1".getBytes()
        );


        doNothing().when(documentServiceImpl).uploadDocument(any(MultipartFile.class), any(DocumentDto.class));


        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.multipart("/document/upload")
                .file(mockFile)
                .file(partIdPart)              // dodajemo i partId
                .contentType(MediaType.MULTIPART_FORM_DATA)
        );


        resultActions.andExpect(status().isOk())
                .andExpect(content().string(containsString("File uploaded successfully: test-document.pdf")));

        verify(documentServiceImpl, times(1)).uploadDocument(any(MultipartFile.class), any(DocumentDto.class));
    }

    @Test
    public void DocumentController_getAll_DocumentsDto() throws Exception {
        List<DocumentDto> documents = Arrays.asList(documentDto1, documentDto2);

        when(documentServiceImpl.findAllDocuments()).thenReturn(documents);

        mockMvc.perform(get("/document/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(documents)));
    }

    @Test
    public void DocumentController_GetDocument_Resource() throws Exception {
        String filename = "test.pdf";
        Resource resource = new ByteArrayResource("dummy pdf content".getBytes());

        when(documentServiceImpl.getDocumentByName(filename)).thenReturn(resource);

        mockMvc.perform(get("/document/{filename}", filename))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_PDF));
    }

    @Test
    public void DocumentController_GetDocument_ShouldReturnBadRequest() throws Exception {
        String filename = "missing.pdf";

        when(documentServiceImpl.getDocumentByName(filename)).thenReturn(null);

        mockMvc.perform(get("/document/{filename}", filename))
                .andExpect(status().isBadRequest()); // jer Spring vraća 400
    }

    @Test
    public void DocumentController_GetByPartId_DocumentsDto_ShouldReturnList() throws Exception {
        Integer partId = 1;
        List<DocumentDto> documents = Arrays.asList(documentDto1, documentDto2);

        when(documentServiceImpl.getByPartId(partId)).thenReturn(documents);

        mockMvc.perform(get("/document/get/part/{id}", partId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].documentName").value("DocumentNameTest"))
                .andExpect(jsonPath("$[0].partId").value(1))
                .andExpect(jsonPath("$[0].documentRoute").value("DocumentRouteTest.pdf"))
                .andExpect(jsonPath("$[1].documentName").value("DocumentNameTest2"))
                .andExpect(jsonPath("$[1].documentRoute").value("DocumentRouteTest2.pdf"));

        verify(documentServiceImpl, times(1)).getByPartId(partId);
    }
    @Test
    public void DocumentController_GetById_DocumentsDtoShouldReturnDocument() throws Exception {
        Integer documentId = 1;

        when(documentServiceImpl.findById(documentId)).thenReturn(documentDto1);

        mockMvc.perform(get("/document/get/{id}", documentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.documentName").value("DocumentNameTest"))
                .andExpect(jsonPath("$.partId").value(1))
                .andExpect(jsonPath("$.documentRoute").value("DocumentRouteTest.pdf"));

        verify(documentServiceImpl, times(1)).findById(documentId);
    }
    @Test
    public void DocumentController_GetById_DocumentsDtoShouldReturnNotFound() throws Exception {
        Integer documentId = 99;

        when(documentServiceImpl.findById(documentId)).thenReturn(null);

        mockMvc.perform(get("/document/get/{id}", documentId))
                .andExpect(status().isNotFound());

        verify(documentServiceImpl, times(1)).findById(documentId);
    }
    @Test
    public void DocumentController_DeleteById_ShouldReturnOk() throws Exception {
        Integer documentId = 1;

        when(documentServiceImpl.deleteById(documentId)).thenReturn(documentDto1);

        mockMvc.perform(delete("/document/delete/{id}", documentId))
                .andExpect(status().isOk());

        verify(documentServiceImpl, times(1)).deleteById(documentId);
    }


    @Test
    public void DocumentController_DeleteById_ShouldReturnClientError_WhenExceptionThrown() throws Exception {
        Integer documentId = 99;

        // Kada deleteById baci exception
        when(documentServiceImpl.deleteById(documentId))
                .thenThrow(new RuntimeException("Error deleting"));

        mockMvc.perform(delete("/document/delete/{id}", documentId))
                .andExpect(status().is4xxClientError());

        verify(documentServiceImpl, times(1)).deleteById(documentId);
    }

}
