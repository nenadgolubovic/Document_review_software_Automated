package com.example.document_review.service.impl;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.mapper.impl.FanBladeMapper;
import com.example.document_review.repository.impl.FanBladeRepository;
import com.example.document_review.service.FanBladeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FanBladeServiceImpl implements FanBladeService {

    private final FanBladeRepository fanBladeRepository;
    private final FanBladeMapper fanBladeMapper;


    public FanBladeServiceImpl(FanBladeRepository fanBladeRepository, FanBladeMapper fanBladeMapper) {
        this.fanBladeRepository = fanBladeRepository;
        this.fanBladeMapper = fanBladeMapper;


    }

    @Override
    @Transactional
    public void delete(Integer partId) throws Exception {
        fanBladeRepository.delete(partId);
    }

    @Override
    public List<FanBladeDto> getAll() throws Exception {
        return fanBladeRepository.findAll().stream().map(entity -> fanBladeMapper.toDto(entity)).collect(Collectors.toList());
    }
    @Override
    public FanBladeDto getById(Integer id) {
        return fanBladeMapper.toDto(fanBladeRepository.findById(id));
    }

    @Override
    @Transactional
    public void save(FanBladeDto fanBladeDto) {
        fanBladeRepository.save(fanBladeMapper.toEntity(fanBladeDto));

    }

    @Override
    public void update(Integer fanBladeId) {

    }
}
