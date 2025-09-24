package com.example.document_review.service;

import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;

public interface UserService {
    void register(UserDto userDto);
    UserDto findById(long id);
    User loginUser(UserDto userDto);
    User findByUsername(String username);
}
