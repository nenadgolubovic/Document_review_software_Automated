package com.example.document_review.mapper.impl;


import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.Role;
import com.example.document_review.entity.User;
import com.example.document_review.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
