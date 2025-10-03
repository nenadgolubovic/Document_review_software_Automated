package com.example.document_review.exception;

public class CommentServiceException extends RuntimeException {
    public CommentServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
