package com.example.document_review.validator.impl;

import com.example.document_review.dto.UserDto;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.validator.Validator;

public class LogInValidator implements Validator <UserDto> {
    @Override
    public void validate(UserDto userDto) throws ValidationException {
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {

        }
    }
}
