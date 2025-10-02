package com.example.document_review.exception;

public class CommentOperationException extends RuntimeException {
    public CommentOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
