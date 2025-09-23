package com.example.document_review.service;

import com.example.document_review.dto.BasicPartDto;

import java.util.List;

public interface BasicPartService {
    List<BasicPartDto> getAll() throws Exception;
    BasicPartDto getById(int id);
    void save(BasicPartDto basicPartDto);

}
