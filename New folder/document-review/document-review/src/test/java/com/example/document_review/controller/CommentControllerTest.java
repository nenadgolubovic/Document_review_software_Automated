package com.example.document_review.controller;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.service.CommentService;
import com.example.document_review.service.impl.CommentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;


@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    private CommentDto commentDto1;
    private CommentDto commentDto2;
    private CommentDto commentDto3;

    @MockitoBean
    private CommentServiceImpl commentServiceImpl;

    @BeforeEach
    public void init() {
        commentDto1 = CommentDto.builder()
                .commentTitle("Test comment title1")
                .comment("Test comment1")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(1).build();

        commentDto2 = CommentDto.builder()
                .commentTitle("Test comment title2")
                .comment("Test comment2")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(false)
                .rate(null).build();

        commentDto3 = CommentDto.builder()
                .commentTitle("Test comment title3")
                .comment("Test comment3")
                .commentDate(LocalDateTime.now())
                .documentId(2)
                .userId(1)
                .isApproved(true)
                .rate(5).build();
    }

    @Test
    public void CommentController_Save_ShouldCallService() throws Exception {

        doNothing().when(commentServiceImpl).save(any(CommentDto.class));

        ResultActions resultActions = mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto1)));

        resultActions.andExpect(status().isOk());

        verify(commentServiceImpl, times(1)).save(refEq(commentDto1));


    }
}
