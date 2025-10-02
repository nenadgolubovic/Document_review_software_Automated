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

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;


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
    public void CommentControllerSaveShouldCallService() throws Exception {

        doNothing().when(commentServiceImpl).save(any(CommentDto.class));

        ResultActions resultActions = mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto1)));

        resultActions.andExpect(status().isOk());

        verify(commentServiceImpl, times(1)).save(refEq(commentDto1));
    }

    @Test
    public void CommentControllerGetAllByDocumentIdShouldReturnListOfComments() throws Exception {

        Integer documentId = 1;
        List<CommentDto> commentList = List.of(commentDto1, commentDto2);

        when(commentServiceImpl.getAllByDocumentId(documentId)).thenReturn(commentList);

        mockMvc.perform(get("/comment/getAllByDocumentId/{documentId}", documentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].commentTitle").value("Test comment title1"))
                .andExpect(jsonPath("$[0].comment").value("Test comment1"))
                .andExpect(jsonPath("$[0].documentId").value(1))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].approved").value(true))
                .andExpect(jsonPath("$[0].rate").value(1))
                .andExpect(jsonPath("$[1].commentTitle").value("Test comment title2"))
                .andExpect(jsonPath("$[1].comment").value("Test comment2"))
                .andExpect(jsonPath("$[1].documentId").value(1))
                .andExpect(jsonPath("$[1].userId").value(1))
                .andExpect(jsonPath("$[1].approved").value(false))
                .andExpect(jsonPath("$[1].rate").value(org.hamcrest.Matchers.nullValue()));

        verify(commentServiceImpl, times(1)).getAllByDocumentId(documentId);
    }
    @Test
    public void CommentControllerFindAllShouldReturnListOfComments() throws Exception {

        when(commentServiceImpl.findAll()).thenReturn(List.of(commentDto1, commentDto2));

        mockMvc.perform(get("/comment/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].commentTitle").value("Test comment title1"))
                .andExpect(jsonPath("$[0].comment").value("Test comment1"))
                .andExpect(jsonPath("$[0].documentId").value(1))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].approved").value(true))
                .andExpect(jsonPath("$[0].rate").value(1))
                .andExpect(jsonPath("$[1].commentTitle").value("Test comment title2"))
                .andExpect(jsonPath("$[1].comment").value("Test comment2"))
                .andExpect(jsonPath("$[1].documentId").value(1))
                .andExpect(jsonPath("$[1].userId").value(1))
                .andExpect(jsonPath("$[1].approved").value(false))
                .andExpect(jsonPath("$[1].rate").value(org.hamcrest.Matchers.nullValue()));

        verify(commentServiceImpl, times(1)).findAll();
    }

    @Test
    public void CommentControllerApproveCommentShouldReturnSuccessMessage() throws Exception {
        Integer commentId = 1;

        doNothing().when(commentServiceImpl).approveComment(commentId);

        mockMvc.perform(put("/comment/approve/{commentId}", commentId))
                .andExpect(status().isOk())
                .andExpect(content().string("Comment approved successfully"));

        verify(commentServiceImpl, times(1)).approveComment(commentId);
    }
    @Test
    public void CommentControllerRejectCommentShouldReturnSuccessMessage() throws Exception {
        Integer commentId = 1;

        doNothing().when(commentServiceImpl).rejectComment(commentId);

        mockMvc.perform(put("/comment/reject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(commentId)))
                .andExpect(status().isOk())
                .andExpect(content().string("Comment rejected successfully"));

        verify(commentServiceImpl, times(1)).rejectComment(commentId);
    }

}
