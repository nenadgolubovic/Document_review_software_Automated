package com.example.document_review.controller;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.dto.CommentDto;
import com.example.document_review.entity.Enums.PartType;
import com.example.document_review.service.DocumentService;
import com.example.document_review.service.impl.BasicPartServiceImpl;
import com.example.document_review.service.impl.CommentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BasicPartController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BasicPartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private BasicPartDto basicPartDto1;
    private BasicPartDto basicPartDto2;
    private BasicPartDto basicPartDto3;

    @MockitoBean
    private BasicPartServiceImpl basicPartServiceImpl;

    @BeforeEach
    public void init() {
        basicPartDto1 = BasicPartDto.builder()
                .partNumber("PartNumberTest1")
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .build();
        basicPartDto2 = BasicPartDto.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .build();
    }

    @Test
    public void BasicPartController_Save_ShouldCallService() throws Exception {

        doNothing().when(basicPartServiceImpl).save(any(BasicPartDto.class));

        ResultActions resultActions = mockMvc.perform(post("/part/basic/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(basicPartDto1)));

        resultActions.andExpect(status().isOk());

        verify(basicPartServiceImpl, times(1)).save(refEq(basicPartDto1));

    }




}
