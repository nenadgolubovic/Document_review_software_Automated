package com.example.document_review.service.impl;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.mapper.impl.CommentMapper;
import com.example.document_review.mapper.impl.UserMapper;
import com.example.document_review.repository.impl.CommentRepository;
import com.example.document_review.repository.impl.UserRepository;
import com.example.document_review.service.NotificationService;
import com.example.document_review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    public NotificationServiceImpl(CommentRepository commentRepository,
                                   UserRepository userRepository,
                                   UserMapper userMapper,
                                   CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }

    private void sendEmail(UserDto userDto, CommentDto commentDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userDto.getEmail());
        message.setSubject("Notification in Document Review Software");
        message.setText(commentDto.getComment());
//        mailSender.send(message);

    }

    public void notifyMentionedUsers(CommentDto commentDto) {
        Pattern pattern = Pattern.compile("@(\\w+)");
        Matcher matcher = pattern.matcher(commentDto.getComment());
        while (matcher.find()) {
            String username = matcher.group(1);
            User user = userRepository.findByUsername(username);



        }
    }

    @Override
    public void sendMailNotification(CommentDto commentDto, UserDto userDto) {


    }
}
