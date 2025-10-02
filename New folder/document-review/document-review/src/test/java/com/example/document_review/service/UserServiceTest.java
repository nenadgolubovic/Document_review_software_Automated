package com.example.document_review.service;

import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.mapper.impl.UserMapper;
import com.example.document_review.repository.impl.UserRepository;
import com.example.document_review.service.impl.UserServiceImpl;
import com.example.document_review.validator.impl.RegistrationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RegistrationValidator registrationValidator;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private User user;
    private UserDto userDto;
    private static final String USERNAME_TEST = "UsernameTest";
    private static final String PASSWORD_TEST = "PasswordTest";
    private static final String FIRST_NAME = "FirstNameTest";
    private static final String LAST_NAME = "LastNameTest";
    private static final String EMAIL = "EmailTest@EmailTest";
    private static final String ENCODED_PASSWORD = "encodedPassword";

    @BeforeEach
    public void setUp() {
        user = User.builder()
            .username(USERNAME_TEST)
            .password(PASSWORD_TEST)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .email(EMAIL)
            .build();
        userDto = UserDto.builder()
            .username(USERNAME_TEST)
            .password(PASSWORD_TEST)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .email(EMAIL)
            .build();
    }

    @Test
    public void userServiceRegister() {

        when(bCryptPasswordEncoder.encode(PASSWORD_TEST)).thenReturn(ENCODED_PASSWORD);
        when(userMapper.toEntity(userDto)).thenReturn(user);

        userServiceImpl.register(userDto);

        verify(registrationValidator).validate(userDto);
        verify(bCryptPasswordEncoder).encode(PASSWORD_TEST);
        assertThat(userDto.getPassword()).isEqualTo(ENCODED_PASSWORD);
        verify(userMapper).toEntity(userDto);
        verify(userRepository).save(user);

        assertThat(userDto.getPassword()).isEqualTo(ENCODED_PASSWORD);

    }

    @Test
    public void userServiceFindByIdUserDto(){

        when(userRepository.findById(1)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto result = userServiceImpl.findById(1);

        verify(userRepository).findById(1);
        verify(userMapper).toDto(user);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(USERNAME_TEST);
        assertThat(result.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(result.getLastName()).isEqualTo(LAST_NAME);
        assertThat(result.getEmail()).isEqualTo(EMAIL);
        assertThat(result.getPassword()).isEqualTo(PASSWORD_TEST);

    }
    @Test
    public void userServiceLoginUserUser(){
        User user = User.builder()
                .username(USERNAME_TEST)
                .password(ENCODED_PASSWORD)
                .build();
        UserDto userDto = UserDto.builder()
                .username(USERNAME_TEST)
                .password(PASSWORD_TEST)
                .build();
        when(userRepository.findByUsername(USERNAME_TEST)).thenReturn(user);
        when(bCryptPasswordEncoder.matches(PASSWORD_TEST, ENCODED_PASSWORD))
                .thenReturn(true);

        User result = userServiceImpl.loginUser(userDto);
        verify(userRepository).findByUsername(USERNAME_TEST);
        verify(bCryptPasswordEncoder).matches(PASSWORD_TEST, ENCODED_PASSWORD);

        assertThat(result).isEqualTo(user);
    }
    @Test
    public void userServiceFindByUsernameUser(){

        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        User result = userServiceImpl.findByUsername(user.getUsername());
        verify(userRepository).findByUsername(user.getUsername());
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getPassword()).isEqualTo(PASSWORD_TEST);
        assertThat(result.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(result.getLastName()).isEqualTo(LAST_NAME);
        assertThat(result.getEmail()).isEqualTo(EMAIL);

    }
}
