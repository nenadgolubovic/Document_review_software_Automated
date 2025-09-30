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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    public void DocumentController_UploadDocument_ShouldCallService() throws Exception {




    }
}
