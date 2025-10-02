package com.example.document_review.service;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.exception.BasicPartServiceException;

import java.util.List;

public interface BasicPartService {
    void delete(Integer partId) throws Exception;
    List<BasicPartDto> getAll() throws BasicPartServiceException;
    BasicPartDto getById(Integer id) throws BasicPartServiceException;
    void save(BasicPartDto basicPartDto) throws BasicPartServiceException;
    void update(Integer basicPartID) throws BasicPartServiceException;

}
