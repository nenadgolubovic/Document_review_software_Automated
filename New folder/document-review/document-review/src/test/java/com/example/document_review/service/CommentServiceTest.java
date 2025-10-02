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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

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

    private Comment comment1;
    private CommentDto commentDto1;
    private Comment comment2;
    private CommentDto commentDto2;
    private Document document;

    @BeforeEach
    public void setUp() {
        comment1 = Comment.builder()
                .commentTitle("Test comment title1")
                .commentText("Test comment1")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(1).build();
        comment2 = Comment.builder()
                .commentTitle("Test comment title2")
                .commentText("Test comment2")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(false)
                .rate(null).build();
        commentDto1 = CommentDto.builder()
                .commentTitle("Test comment title1")
                .commentText("Test comment1")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(true)
                .rate(1).build();
        commentDto2 = CommentDto.builder()
                .commentTitle("Test comment title2")
                .commentText("Test comment2")
                .commentDate(LocalDateTime.now())
                .documentId(1)
                .userId(1)
                .isApproved(false)
                .rate(null).build();
        document = Document.builder()
                .documentName("DocumentNameTest")
                .partId(1)
                .documentRoute("DocumentRouteTest.pdf")
                .documentDate(LocalDate.now())
                .build();
    }

    @Test
    public void commentServiceImplSave() throws ValidationException {

        when(documentRepository.findById(1)).thenReturn(document);
        when(commentMapper.toEntity(commentDto1)).thenReturn(comment1);
        doNothing().when(documentFileValidator).validateDocument(document);
        doNothing().when(commentSaveValidator).validate(commentDto1);
        commentServiceImpl.save(commentDto1);

        verify(commentRepository).save(comment1);

    }

    @Test
    public void commentServiceImplFindAllReturnCommentDtos() throws Exception {


        when(commentRepository.findAll()).thenReturn(List.of(comment1, comment2));
        when(commentMapper.toDto(comment1)).thenReturn(commentDto1);
        when(commentMapper.toDto(comment2)).thenReturn(commentDto2);

        List<CommentDto> commentDtos = commentServiceImpl.findAll();

        assertThat(commentDtos).hasSize(2).containsExactly(commentDto1, commentDto2);
    }

    @Test
    public void commentServiceImplApproveComment() throws Exception {
        when(commentRepository.findById(1)).thenReturn(comment1);
        commentServiceImpl.approveComment(1);

        assertThat(comment1.isApproved()).isTrue();
        verify(commentRepository, times(1)).update(comment1);

    }

    @Test
    public void commentServiceImplRateCommentWhenNotRated() throws Exception {
        when(commentRepository.findById(1)).thenReturn(comment1);
        commentServiceImpl.rateComment(1,5);
        assertThat(comment1.getRate()).isEqualTo(5);
    }

    @Test
    public void commentServiceImplRateCommentWhenRated() throws Exception {
        when(commentRepository.findById(1)).thenReturn(comment1);
        commentServiceImpl.rateComment(1,2);
        assertThat(comment1.getRate()).isEqualTo(2);
    }

    @Test
    public void commentServiceImplGetAllByDocumentIdCommentDtos(){


        List<Comment> comments = Arrays.asList(comment1, comment2);
        when(commentRepository.getAllByDocumentId(1)).thenReturn(comments);
        when(commentMapper.toDto(comment1)).thenReturn(commentDto1);
        when(commentMapper.toDto(comment2)).thenReturn(commentDto2);

        List<CommentDto> result = commentServiceImpl.getAllByDocumentId(1);
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(commentDto1, commentDto2);
        verify(commentRepository, times(1)).getAllByDocumentId(1);
        verify(commentMapper, times(1)).toDto(comment1);
        verify(commentMapper, times(1)).toDto(comment2);
    }
}
