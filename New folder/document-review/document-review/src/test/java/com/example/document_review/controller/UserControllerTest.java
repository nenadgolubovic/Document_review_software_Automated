package com.example.document_review.controller;

import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.service.UserService;
import com.example.document_review.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoBeans;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userServiceImpl;

    @Test
    public void register_ShouldReturnBadRequest_WhenUserExists() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("existingUser");

        when(userServiceImpl.findByUsername("existingUser")).thenReturn(new User());

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username already exists"));

        verify(userServiceImpl, never()).register(any(UserDto.class));
    }

    @Test
    public void register_ShouldReturnBadRequest_WhenValidationException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("newUser");

        when(userServiceImpl.findByUsername("newUser")).thenReturn(null);
        doThrow(new ValidationException("Invalid data")).when(userServiceImpl).register(any(UserDto.class));

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid data"));

        verify(userServiceImpl, times(1)).register(any(UserDto.class));
    }

    @Test
    public void register_ShouldReturnOk_WhenUserRegistered() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("newUser");

        when(userServiceImpl.findByUsername("newUser")).thenReturn(null);

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));

        verify(userServiceImpl, times(1)).register(any(UserDto.class));
    }
}
