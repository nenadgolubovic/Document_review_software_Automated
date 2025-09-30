package com.example.document_review.controller;


import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.entity.Enums.PartType;
import com.example.document_review.service.impl.BasicPartServiceImpl;
import com.example.document_review.service.impl.FanBladeServiceImpl;
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

@WebMvcTest(controllers = FanBladeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class FanBladeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private FanBladeDto fanBladeDto1;
    private FanBladeDto fanBladeDto2;
    private FanBladeDto fanBladeDto3;

    @MockitoBean
    private FanBladeServiceImpl fanBladeServiceImpl;

    @BeforeEach
    public void init() {
        fanBladeDto1 = FanBladeDto.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();
    }
    @Test
    public void FanBladeController_Save_ShouldCallService() throws Exception {

        doNothing().when(fanBladeServiceImpl).save(any(FanBladeDto.class));

        ResultActions resultActions = mockMvc.perform(post("/part/fanBlades/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fanBladeDto1)));

        resultActions.andExpect(status().isOk());

        verify(fanBladeServiceImpl, times(1)).save(refEq(fanBladeDto1));


    }
}
