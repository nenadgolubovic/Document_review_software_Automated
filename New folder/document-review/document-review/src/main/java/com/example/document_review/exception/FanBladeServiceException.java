package com.example.document_review.exception;

public class FanBladeServiceException extends RuntimeException {
    public FanBladeServiceException(String message) {
        super(message);
    }
    public FanBladeServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
