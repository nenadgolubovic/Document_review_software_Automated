package com.example.document_review.validator.impl;

import com.example.document_review.dto.UserDto;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.validator.Validator;
import org.springframework.stereotype.Component;


@Component
public class LogInValidator implements Validator <UserDto> {
    @Override
    public void validate(UserDto userDto) throws ValidationException {
        if (userDto.getUsername() == null || userDto.getEmail().isEmpty()) {
            throw new ValidationException("Username is empty");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new ValidationException("Password is empty");
        }

    }
}
