package com.example.document_review.service;

import com.example.document_review.dto.BasicPartDto;

import java.util.List;

public interface BasicPartService {
    void delete(Integer partId) throws Exception;
    List<BasicPartDto> getAll() throws Exception;
    BasicPartDto getById(Integer id);
    void save(BasicPartDto basicPartDto);
    void update(Integer basicPartID);

}
