package com.example.document_review.service;

import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.exception.FanBladeServiceException;

import java.util.List;

public interface FanBladeService {
    void delete(Integer partId) throws FanBladeServiceException;
    List<FanBladeDto> getAll() throws FanBladeServiceException;
    FanBladeDto getById(Integer id) throws FanBladeServiceException;
    void save(FanBladeDto fanBladeDto) throws FanBladeServiceException;
    void update(Integer fanBladeId) throws FanBladeServiceException;

}
