package com.example.document_review.service;

import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.mapper.impl.UserMapper;
import com.example.document_review.repository.impl.UserRepository;
import com.example.document_review.service.impl.UserServiceImpl;
import com.example.document_review.validator.impl.RegistrationValidator;
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


    @Test
    public void UserServiceRegister() {

        User user = User.builder()
                .username("UsernameTest")
                .password("PasswordTest")
                .firstName("FirstNameTest")
                .lastName("LastNameTest")
                .email("EmailTest@EmailTest")
                .build();
        UserDto userDto = UserDto.builder()
                .username("UsernameTest")
                .password("PasswordTest")
                .firstName("FirstNameTest")
                .lastName("LastNameTest")
                .email("EmailTest@EmailTest")
                .build();

        when(bCryptPasswordEncoder.encode("PasswordTest")).thenReturn("encodedPassword");
        when(userMapper.toEntity(userDto)).thenReturn(user);

        userServiceImpl.register(userDto);

        verify(registrationValidator).validate(userDto);
        verify(bCryptPasswordEncoder).encode("PasswordTest");
        assertThat(userDto.getPassword()).isEqualTo("encodedPassword");
        verify(userMapper).toEntity(userDto);
        verify(userRepository).save(user);

        assertThat(userDto.getPassword()).isEqualTo("encodedPassword");

    }

    @Test
    public void UserServiceFindByIdUserDto(){
        User user = User.builder()
                .username("UsernameTest")
                .password("PasswordTest")
                .firstName("FirstNameTest")
                .lastName("LastNameTest")
                .email("EmailTest@EmailTest")
                .build();
        UserDto userDto = UserDto.builder()
                .username("UsernameTest")
                .password("PasswordTest")
                .firstName("FirstNameTest")
                .lastName("LastNameTest")
                .email("EmailTest@EmailTest")
                .build();

        when(userRepository.findById(1)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto result = userServiceImpl.findById(1);

        verify(userRepository).findById(1);
        verify(userMapper).toDto(user);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("UsernameTest");
        assertThat(result.getFirstName()).isEqualTo("FirstNameTest");
        assertThat(result.getLastName()).isEqualTo("LastNameTest");
        assertThat(result.getEmail()).isEqualTo("EmailTest@EmailTest");
        assertThat(result.getPassword()).isEqualTo("PasswordTest");

    }
    @Test
    public void UserServiceLoginUserUser(){
        User user = User.builder()
                .username("UsernameTest")
                .password("EncodedPassword")
                .build();
        UserDto userDto = UserDto.builder()
                .username("UsernameTest")
                .password("PasswordTest")
                .build();
        when(userRepository.findByUsername("UsernameTest")).thenReturn(user);
        when(bCryptPasswordEncoder.matches("PasswordTest", "EncodedPassword"))
                .thenReturn(true);

        User result = userServiceImpl.loginUser(userDto);
        verify(userRepository).findByUsername("UsernameTest");
        verify(bCryptPasswordEncoder).matches("PasswordTest", "EncodedPassword");

        assertThat(result).isEqualTo(user);
    }
    @Test
    public void UserServiceFindByUsernameUser(){

        User user = User.builder()
                .username("UserNameTest")
                .password("PasswordTest")
                .firstName("FirstNameTest")
                .lastName("LastNameTest")
                .email("EmailTest@EmailTest")
                .build();
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        User result = userServiceImpl.findByUsername(user.getUsername());
        verify(userRepository).findByUsername(user.getUsername());
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getPassword()).isEqualTo("PasswordTest");
        assertThat(result.getFirstName()).isEqualTo("FirstNameTest");
        assertThat(result.getLastName()).isEqualTo("LastNameTest");
        assertThat(result.getEmail()).isEqualTo("EmailTest@EmailTest");

    }
}
