package com.example.document_review.service.impl;

import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.mapper.impl.UserMapper;
import com.example.document_review.repository.impl.UserRepository;
import com.example.document_review.service.UserService;
import com.example.document_review.validator.impl.LogInValidator;
import com.example.document_review.validator.impl.RegistrationValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RegistrationValidator registrationValidator;
    private final LogInValidator logInValidator;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper, UserRepository userRepository, RegistrationValidator registrationValidator, LogInValidator logInValidator) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.registrationValidator = registrationValidator;
        this.logInValidator = logInValidator;
    }

    @Override
    public void register(UserDto userDto) {

        registrationValidator.validate(userDto);
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);


    }

    @Override
    public UserDto findById(long id) {
        return null;
    }

    @Override
    public User loginUser(UserDto userDto) throws EntityNotFoundException {
        logInValidator.validate(userDto);
        User user = userMapper.toEntity(userDto);
        User loginUser = userRepository.findByUsername(user.getUsername());
        if(loginUser == null) {
            throw new EntityNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
