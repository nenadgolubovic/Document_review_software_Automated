package com.example.document_review.mapper.impl;


import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public User toEntity(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto toDto(User user) {
        return null;
    }
}
