package com.example.document_review.controller;


import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.entity.enums.PartType;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    private final String URL_GETFANBLADEID = "/part/fanBlades/";
    private final String PART_NUMBER_TEST= "PartNumberTest";
    @MockitoBean
    private FanBladeServiceImpl fanBladeServiceImpl;

    @BeforeEach
    public void init() {
        fanBladeDto1 = FanBladeDto.builder()
                .partNumber(PART_NUMBER_TEST)
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FAN_BLADE)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();
        fanBladeDto2 = FanBladeDto.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.FAN_BLADE)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2")
                .build();
    }
    @Test
    public void fanBladeControllerSaveShouldCallService() throws Exception {

        doNothing().when(fanBladeServiceImpl).save(any(FanBladeDto.class));

        ResultActions resultActions = mockMvc.perform(post("/part/fanBlades/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fanBladeDto1)));

        resultActions.andExpect(status().isOk());

        verify(fanBladeServiceImpl, times(1)).save(refEq(fanBladeDto1));
    }

    @Test
    public void fanBladeControllerGetByIdFanBladeDtos() throws Exception {
        Integer fanBladeId = 1;
        String url = URL_GETFANBLADEID + fanBladeId;

        when(fanBladeServiceImpl.getById(fanBladeId)).thenReturn(fanBladeDto1);

        mockMvc.perform(get(url, fanBladeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.partNumber").value(PART_NUMBER_TEST))
                .andExpect(jsonPath("$.description").value("DescriptionTest"))
                .andExpect(jsonPath("$.serialNumber").value("SerialNumberTest"))
                .andExpect(jsonPath("$.type").value("FAN_BLADE"))
                .andExpect(jsonPath("$.cyclesSinceNew").value("CycleSinceNewTest"))
                .andExpect(jsonPath("$.timeSinceNew").value("TimeSinceNewTest"))
                .andExpect(jsonPath("$.momentWeight").value("MomentWeightTest"));

        verify(fanBladeServiceImpl, times(1)).getById(fanBladeId);
    }
    @Test
    public void fanBladeControllerGetByIdNotFoundFanBladeDtos() throws Exception {
        Integer fanBladeId = 99;
        String url = URL_GETFANBLADEID + fanBladeId;

        when(fanBladeServiceImpl.getById(fanBladeId)).thenReturn(null);

        mockMvc.perform(get(url, fanBladeId))
                .andExpect(status().isNotFound());

        verify(fanBladeServiceImpl, times(1)).getById(fanBladeId);
    }
    @Test
    public void fanBladeControllerFindAllFanBladeDtos() throws Exception {
        List<FanBladeDto> fanBladeList = List.of(fanBladeDto1, fanBladeDto2);
        when(fanBladeServiceImpl.getAll()).thenReturn(fanBladeList);

        mockMvc.perform(get("/part/fanBlades/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].partNumber").value(PART_NUMBER_TEST))
                .andExpect(jsonPath("$[1].partNumber").value("PartNumberTest2"));

        verify(fanBladeServiceImpl, times(1)).getAll();
    }
    @Test
    public void fanBladeControllerDeleteShouldReturnOkWhenFanBladeExists() throws Exception {
        Integer fanBladeId = 1;
        String url = URL_GETFANBLADEID + fanBladeId;
        // Mock servisa da postoji DTO
        when(fanBladeServiceImpl.getById(fanBladeId)).thenReturn(fanBladeDto1);
        doNothing().when(fanBladeServiceImpl).delete(fanBladeId);

        mockMvc.perform(delete(url, fanBladeId))
                .andExpect(status().isOk());

        verify(fanBladeServiceImpl, times(1)).getById(fanBladeId);
        verify(fanBladeServiceImpl, times(1)).delete(fanBladeId);
    }

    @Test
    public void fanBladeControllerDeleteShouldReturnNotFoundWhenFanBladeDoesNotExist() throws Exception {
        Integer fanBladeId = 99;
        String url = URL_GETFANBLADEID + fanBladeId;

        when(fanBladeServiceImpl.getById(fanBladeId)).thenReturn(null);

        mockMvc.perform(delete(url, fanBladeId))
                .andExpect(status().isNotFound());

        verify(fanBladeServiceImpl, times(1)).getById(fanBladeId);
        verify(fanBladeServiceImpl, never()).delete(fanBladeId);
    }

    @Test
    public void fanBladeControllerUpdateShouldCallService() throws Exception {
        Integer fanBladeId = 1;
        String url = URL_GETFANBLADEID + fanBladeId;

        doNothing().when(fanBladeServiceImpl).update(fanBladeId);

        mockMvc.perform(put(url, fanBladeId))
                .andExpect(status().isOk());

        verify(fanBladeServiceImpl, times(1)).update(fanBladeId);
    }


}
