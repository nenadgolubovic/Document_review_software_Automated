package com.example.document_review.service;

import com.example.document_review.dto.CommentDto;
import com.example.document_review.entity.Comment;
import com.example.document_review.entity.Document;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.mapper.impl.CommentMapper;
import com.example.document_review.repository.impl.CommentRepository;
import com.example.document_review.repository.impl.DocumentRepository;
import com.example.document_review.service.impl.CommentServiceImpl;
import com.example.document_review.validator.impl.CommentSaveValidator;
import com.example.document_review.validator.impl.DocumentFileValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentFileValidator documentFileValidator;

    @Mock
    private CommentSaveValidator commentSaveValidator;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @Test
    public void CommentServiceImpl_Save() throws ValidationException {

        Comment comment = Comment.builder()
                .commentTitle("Test comment title")
                .comment("Test comment")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(5).build();
        CommentDto commentDto = CommentDto.builder()
                .commentTitle("Test comment title")
                .comment("Test comment")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(5).build();

        Document document = Document.builder()
                .documentName("DocumentNameTest")
                .partId(1)
                .documentRoute("DocumentRouteTest.pdf")
                .documentDate(LocalDate.now())
                .build();


        when(documentRepository.findById(1)).thenReturn(document);
        when(commentMapper.toEntity(commentDto)).thenReturn(comment);
        doNothing().when(documentFileValidator).validateDocument(document);
        doNothing().when(commentSaveValidator).validate(commentDto);
        commentServiceImpl.save(commentDto);

        verify(commentRepository).save(comment);

    }

//    @Test


}
