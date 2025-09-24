package com.example.document_review.validator;

import com.example.document_review.exception.ValidationException;

public interface Validator<Dto> {
    void validate(Dto dto) throws ValidationException;
}
