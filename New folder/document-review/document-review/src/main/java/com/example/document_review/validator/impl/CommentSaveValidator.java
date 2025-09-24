package com.example.document_review.validator.impl;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.validator.Validator;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;


@Component
public class CommentSaveValidator implements Validator <CommentDto>{
    @Override
    public void validate(CommentDto commentDto) throws ValidationException {
        if(commentDto.getComment() == null || commentDto.getComment().isEmpty()){
            throw new ValidationException("Comment is empty");
        }
        if(commentDto.getComment().length() > 100){
            throw new ValidationException("Comment is too long, please keep under 100 caracters");}

        if(commentDto.getCommentTitle() == null || commentDto.getCommentTitle().isEmpty()){
            throw new ValidationException("Comment title is empty");
        }
        if(commentDto.getCommentTitle().length() > 50){
            throw new ValidationException("Comment Title is too long, please keep under 100 caracters");}

        if(commentDto.getDocument() == null){
            throw new ValidationException("Not assigned Document to comment");
        }
    }
}
