package com.example.document_review.service;

import com.example.document_review.dto.PartDto;

import java.util.List;

public interface PartService{
    void save(PartDto partDto);

    PartDto getById(int id);
    List<PartDto> getAll() throws Exception;

    void delete(Integer id) throws Exception;
}
