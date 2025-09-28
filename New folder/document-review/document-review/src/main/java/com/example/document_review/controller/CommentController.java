package com.example.document_review.controller;
import com.example.document_review.dto.CommentDto;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.service.CommentService;
import jakarta.persistence.PostUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public void save(@RequestBody @Validated CommentDto commentDto) {
        commentService.save(commentDto);
    }

    @GetMapping("/getAllByDocumentId/{documentId}")
    public ResponseEntity<List<CommentDto>> getAllByDocumentId(
           @PathVariable Integer documentId) {
        return new ResponseEntity<>(commentService.getAllByDocumentId(documentId), HttpStatus.OK);

    }



    @GetMapping("/all")
    public ResponseEntity<List<CommentDto>> findAll() throws Exception {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/approve/{commentId}")
    public ResponseEntity<String> approveComment(@PathVariable Integer commentId) throws Exception {
        commentService.approveComment(commentId);
        return ResponseEntity.ok("Comment approved successfully");
    }

    @PutMapping("/reject")
    public ResponseEntity<String> rejectComment(@RequestBody Integer commentId) throws Exception {
        commentService.rejectComment(commentId);
        return ResponseEntity.ok("Comment rejected successfully");
    }


    @PutMapping("/rateComment/{commentId}/{newRate}")
    public ResponseEntity<String> rateComment(@PathVariable Integer commentId,
                                              @PathVariable Integer newRate)  throws Exception {
        commentService.rateComment(commentId, newRate);
        return ResponseEntity.ok("Comment rate successfully");
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseStatusException handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }

}
