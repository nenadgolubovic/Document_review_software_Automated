package com.example.document_review.service;

import com.example.document_review.dto.FanBladeDto;

import java.util.List;

public interface FanBladeService {
    void delete(Integer partId) throws Exception;
    List<FanBladeDto> getAll() throws Exception;
    FanBladeDto getById(Integer id);
    void save(FanBladeDto fanBladeDto);
    void update(Integer fanBladeId);

}
