package com.example.document_review.validator;

import com.example.document_review.exception.ValidationException;

public interface Validator<D> {
    void validate(D dto) throws ValidationException;
}
