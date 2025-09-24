package com.example.document_review.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody ResponseStatusException handleException(Exception e) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
