package com.example.document_review.service.impl;

import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.mapper.impl.UserMapper;
import com.example.document_review.repository.impl.UserRepository;
import com.example.document_review.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);

    }

    @Override
    public UserDto findById(long id) {
        return null;
    }

    @Override
    public void loginUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.findByUsername(user.getUsername());
    }
}
