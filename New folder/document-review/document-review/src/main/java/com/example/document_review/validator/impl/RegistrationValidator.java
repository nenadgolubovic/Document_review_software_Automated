package com.example.document_review.validator.impl;

import com.example.document_review.dto.UserDto;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.validator.Validator;
import org.springframework.stereotype.Component;


@Component
public class RegistrationValidator implements Validator <UserDto> {
    @Override
    public void validate(UserDto userDto) throws ValidationException {
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            throw new ValidationException("Username is required");
        }
        if (!userDto.getMatchingPassword().equals(userDto.getPassword())) {
            throw new ValidationException("Passwords do not match");
        }
    }
}
