package com.example.document_review.exception;

public class RepositoryOperationException extends RuntimeException {
    public RepositoryOperationException(String message) {
        super(message);
    }

    public RepositoryOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}