package com.example.document_review.service;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.exception.BasicPartServiceException;
import com.example.document_review.exception.EntityNotFoundException;

import java.util.List;

public interface BasicPartService {
    void delete(Integer partId) throws EntityNotFoundException;
    List<BasicPartDto> getAll() throws EntityNotFoundException;
    BasicPartDto getById(Integer id) throws EntityNotFoundException;
    void save(BasicPartDto basicPartDto) throws BasicPartServiceException;
    void update(Integer basicPartID) throws BasicPartServiceException;

}
