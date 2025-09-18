package com.example.document_review.service.impl;

import com.example.document_review.dto.EngineDto;
import com.example.document_review.mapper.impl.EngineMapper;
import com.example.document_review.repository.impl.EngineRepository;
import com.example.document_review.service.EngineService;
import org.springframework.stereotype.Service;

@Service
public class EngineServiceImpl implements EngineService {

    private EngineRepository engineRepository;
    private EngineMapper engineMapper;


    public EngineServiceImpl(EngineRepository engineRepository, EngineMapper engineMapper) {
        this.engineRepository = engineRepository;
        this.engineMapper = engineMapper;
    }


    @Override
    public void save(EngineDto engineDto) {
        engineRepository.save(engineMapper.toEntity(engineDto));
    }
}
