package com.example.document_review.controller;

import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.service.UserService;
import com.example.document_review.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoBeans;
import org.springframework.test.web.servlet.MockMvc;
import java.security.Principal;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private UserDto userDto1;
    private User user;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserServiceImpl userServiceImpl;

    private final String USER_NOT_FOUND_MESSAGE = "User not found";
    private final String URL_REGISTER = "/user/register";

    @BeforeEach
    public void init() {
        userDto1 = UserDto.builder()
                .username("UsernameTest1")
                .password("PasswordTest1")
                .firstName("FirstNameTest1")
                .lastName("LastNameTest1")
                .email("EmailTest@EmailTest1")
                .build();
        user = User.builder()
                .username("UsernameTest")
                .password("PasswordTest")
                .firstName("FirstNameTest")
                .lastName("LastNameTest")
                .email("EmailTest@EmailTest")
                .build();
    }

    @Test
    public void userControllerRegisterShouldReturnBadRequestWhenUserExists() throws Exception {


        when(userServiceImpl.findByUsername(userDto1.getUsername())).thenReturn(new User());

        mockMvc.perform(post(URL_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto1)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username already exists"));

        verify(userServiceImpl, never()).register(any(UserDto.class));
    }

    @Test
    public void userControllerRegisterShouldReturnBadRequestWhenValidationException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("newUser");

        when(userServiceImpl.findByUsername("newUser")).thenReturn(null);
        doThrow(new ValidationException("Invalid data")).when(userServiceImpl).register(any(UserDto.class));

        mockMvc.perform(post(URL_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid data"));

        verify(userServiceImpl, times(1)).register(any(UserDto.class));
    }

    @Test
    public void userControllerRegisterShouldReturnOkWhenUserRegistered() throws Exception {


        when(userServiceImpl.findByUsername(userDto1.getUsername())).thenReturn(null);

        mockMvc.perform(post(URL_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto1)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));

        verify(userServiceImpl, times(1)).register(any(UserDto.class));
    }
    @Test
    public void userControllerHomeShouldReturnNotAuthenticatedWhenPrincipalIsNull() throws Exception {
        mockMvc.perform(get("/user/home"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Not authenticated"));
    }

    @Test
    public void userControllerHomeShouldReturnUserNotFoundWhenUserDoesNotExist() throws Exception {
        Principal principal = () -> "nonexistentUser";

        when(userServiceImpl.findByUsername("nonexistentUser")).thenReturn(null);

        mockMvc.perform(get("/user/home").principal(principal))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(USER_NOT_FOUND_MESSAGE));
    }

    @Test
    void userControllerLoginShouldReturnLoggedUserDtoWhenCredentialsAreCorrect() throws Exception {

        when(userServiceImpl.loginUser(any(UserDto.class))).thenReturn(user);

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()));

        verify(userServiceImpl, times(1)).loginUser(refEq(userDto1));
    }

    @Test
    void userControllerShouldReturnBadRequestWhenLoginFails() throws Exception {
        doThrow(new EntityNotFoundException("User not found"))
                .when(userServiceImpl).loginUser(any(UserDto.class));

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto1)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value(containsString(USER_NOT_FOUND_MESSAGE)));

        verify(userServiceImpl, times(1)).loginUser(refEq(userDto1));
    }


    @Test
    void userControllerLogoutUserShouldReturnOk() throws Exception {
        mockMvc.perform(post("/user/logout"))
                .andExpect(status().isOk())
                .andExpect(content().string("Logout successful"));
    }

    @Test
    void userControllerLogoutUserShouldInvalidateSessionAndRemoveCookie() throws Exception {

        UserService mockUserService = Mockito.mock(UserService.class);

        UserController controller = new UserController(mockUserService);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        ResponseEntity<String> result = controller.logoutUser(request, response);

        assertEquals("Logout successful", result.getBody());
        assertNull(request.getSession(false)); // session je invalidiran
        assertEquals(0, response.getCookie("JSESSIONID").getMaxAge()); // cookie obrisan
    }


    @Test
    void findByIdShouldReturnUserDtoWhenUserExists() throws Exception {

        when(userServiceImpl.findById(1)).thenReturn(userDto1);


        mockMvc.perform(get("/user/findById/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userDto1.getUsername()))
                .andExpect(jsonPath("$.email").value(userDto1.getEmail()));

        verify(userServiceImpl, times(1)).findById(1);
    }



}


