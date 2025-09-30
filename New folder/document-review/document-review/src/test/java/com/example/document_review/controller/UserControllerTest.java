package com.example.document_review.controller;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.dto.PartDto;
import com.example.document_review.dto.UserDto;
import com.example.document_review.service.impl.BasicPartServiceImpl;
import com.example.document_review.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @MockitoBean
    private UserServiceImpl userServiceImpl;

    private UserDto userDto;

    @BeforeEach
    public void init() {
        userDto = UserDto.builder()
            .username("UsernameTest")
            .password("PasswordTest")
            .firstName("FirstNameTest")
            .lastName("LastNameTest")
            .email("EmailTest@EmailTest")
            .build();
    }
}
