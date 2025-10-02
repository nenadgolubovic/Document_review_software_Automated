package com.example.document_review.exception;

public class BasicPartServiceException extends RuntimeException {
    public BasicPartServiceException(String message) {
        super(message);
    }
    public BasicPartServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}