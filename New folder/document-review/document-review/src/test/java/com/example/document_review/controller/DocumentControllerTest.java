package com.example.document_review.controller;


import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.dto.DocumentDto;
import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.entity.Document;
import com.example.document_review.entity.Enums.PartType;
import com.example.document_review.service.impl.BasicPartServiceImpl;
import com.example.document_review.service.impl.DocumentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        DocumentDto documentDto = DocumentDto.builder()
                .documentName("DocumentNameTest")
                .partId(1)
                .documentRoute("DocumentRouteTest.pdf")
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

}
