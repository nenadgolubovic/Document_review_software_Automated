package com.example.document_review.controller;


import com.example.document_review.dto.CommentDto;
import com.example.document_review.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public void save(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
    }

}
