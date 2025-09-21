package com.example.document_review.controller;
import com.example.document_review.dto.CommentDto;
import com.example.document_review.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping("/all")
    public ResponseEntity<List<CommentDto>> findAll() throws Exception {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    } 
    
}
