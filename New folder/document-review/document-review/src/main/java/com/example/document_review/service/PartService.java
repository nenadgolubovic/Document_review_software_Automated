package com.example.document_review.service;

import com.example.document_review.dto.PartDto;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.exception.PartServiceException;

import java.util.List;

public interface PartService{
    void save(PartDto partDto) throws PartServiceException;
    PartDto getById(int id)throws EntityNotFoundException;
    List<PartDto> getAll() throws EntityNotFoundException;
    void delete(Integer id) throws EntityNotFoundException;
}
