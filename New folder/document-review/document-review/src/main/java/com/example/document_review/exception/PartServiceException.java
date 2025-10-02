package com.example.document_review.exception;

public class PartServiceException extends RuntimeException {
    public PartServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PartServiceException(String message) {
        super(message);
    }
}