package com.example.document_review.service;

import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.exception.EntityNotFoundException;

public interface UserService {
    void register(UserDto userDto);
    UserDto findById(Integer id);
    User loginUser(UserDto userDto) throws EntityNotFoundException;
    User findByUsername(String username);
}
