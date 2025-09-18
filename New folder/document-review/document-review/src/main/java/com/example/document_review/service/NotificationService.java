package com.example.document_review.service;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.dto.UserDto;

public interface NotificationService {
    void sendMailNotification(CommentDto commentDto, UserDto userDto);
}
