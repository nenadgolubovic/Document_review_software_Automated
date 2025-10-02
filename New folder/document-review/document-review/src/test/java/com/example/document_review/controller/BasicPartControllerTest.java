package com.example.document_review.controller;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.entity.enums.PartType;
import com.example.document_review.service.impl.BasicPartServiceImpl;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    private static final String BASIC_PART_ID_PATH = "/part/basic/";
    private static final String PART_NUMBER_TEST_1 = "PartNumberTest1";



    @MockitoBean
    private BasicPartServiceImpl basicPartServiceImpl;

    @BeforeEach
    public void init() {
        basicPartDto1 = BasicPartDto.builder()
                .partNumber(PART_NUMBER_TEST_1)
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.BASIC)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .build();
        basicPartDto2 = BasicPartDto.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.BASIC)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .build();
    }

    @Test
    public void basicPartControllerSaveShouldCallService() throws Exception {

        doNothing().when(basicPartServiceImpl).save(any(BasicPartDto.class));

        ResultActions resultActions = mockMvc.perform(post("/part/basic/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(basicPartDto1)));

        resultActions.andExpect(status().isOk());

        verify(basicPartServiceImpl, times(1)).save(refEq(basicPartDto1));

    }
    @Test
    public void basicPartControllerGetByIdBasicPartDtos() throws Exception {
        Integer basicPartId = 1;
        String url = BASIC_PART_ID_PATH + basicPartId;

        when(basicPartServiceImpl.getById(basicPartId)).thenReturn(basicPartDto1);

        mockMvc.perform(get(url, basicPartId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.partNumber").value(PART_NUMBER_TEST_1))
                .andExpect(jsonPath("$.description").value("DescriptionTest1"))
                .andExpect(jsonPath("$.serialNumber").value("SerialNumberTest1"))
                .andExpect(jsonPath("$.type").value("BASIC"))
                .andExpect(jsonPath("$.cyclesSinceNew").value("CycleSinceNewTest1"))
                .andExpect(jsonPath("$.timeSinceNew").value("TimeSinceNewTest1"));

        verify(basicPartServiceImpl, times(1)).getById(basicPartId);
    }
    @Test
    public void basicPartControllerGetByIdNotFoundBasicPartDtos() throws Exception {
        Integer basicPartId = 99;
        String url = BASIC_PART_ID_PATH + basicPartId;

        when(basicPartServiceImpl.getById(basicPartId)).thenReturn(null);

        mockMvc.perform(get(url, basicPartId))
                .andExpect(status().isNotFound());

        verify(basicPartServiceImpl, times(1)).getById(basicPartId);
    }


    @Test
    public void basicPartControllerFindAllBasicPartDtos() throws Exception {
        List<BasicPartDto> basicPertList = List.of(basicPartDto1, basicPartDto2);
        when(basicPartServiceImpl.getAll()).thenReturn(basicPertList);

        mockMvc.perform(get("/part/basic/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].partNumber").value(PART_NUMBER_TEST_1))
                .andExpect(jsonPath("$[1].partNumber").value("PartNumberTest2"));

        verify(basicPartServiceImpl, times(1)).getAll();
    }
    @Test
    public void basicPartControllerDeleteShouldReturnOkWhenBasicPartExists() throws Exception {
        Integer basicPartId = 1;
        String url = BASIC_PART_ID_PATH + basicPartId;

        when(basicPartServiceImpl.getById(basicPartId)).thenReturn(basicPartDto1);
        doNothing().when(basicPartServiceImpl).delete(basicPartId);

        mockMvc.perform(delete(url, basicPartId))
                .andExpect(status().isOk());

        verify(basicPartServiceImpl, times(1)).getById(basicPartId);
        verify(basicPartServiceImpl, times(1)).delete(basicPartId);
    }



    @Test
    public void basicPartControllerDeleteShouldReturnNotFoundWhenBasicPartDoesNotExist() throws Exception {
        Integer basicPartId = 99;
        String url = BASIC_PART_ID_PATH + basicPartId;

        when(basicPartServiceImpl.getById(basicPartId)).thenReturn(null);

        mockMvc.perform(delete(url, basicPartId))
                .andExpect(status().isNotFound());

        verify(basicPartServiceImpl, times(1)).getById(basicPartId);
        verify(basicPartServiceImpl, never()).delete(basicPartId);
    }

    @Test
    public void basicPartControllerUpdateShouldCallService() throws Exception {
        Integer basicPartId = 1;
        String url = BASIC_PART_ID_PATH + basicPartId;

        doNothing().when(basicPartServiceImpl).update(basicPartId);

        mockMvc.perform(put(url, basicPartId))
                .andExpect(status().isOk());

        verify(basicPartServiceImpl, times(1)).update(basicPartId);
    }




}
