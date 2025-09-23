package com.example.document_review.service;

import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;

import java.util.List;

public interface UserService {
    void register(UserDto userDto);
    UserDto findById(long id);
    void loginUser(UserDto userDto);
    User findByUsername(String username);
}
