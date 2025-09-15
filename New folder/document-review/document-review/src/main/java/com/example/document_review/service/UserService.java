package com.example.document_review.service;

import com.example.document_review.dto.UserDto;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);
    void update(UserDto userDto);
    void delete(UserDto userDto);
    List<UserDto> findAll();
    UserDto findById(long id);
    void changePassword(UserDto userDto);
    void changeName(UserDto userDto);
    void changeEmail(UserDto userDto);
    void changeRole(UserDto userDto);


}
