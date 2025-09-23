package com.example.document_review.service.impl;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.dto.CommentDto;
import com.example.document_review.mapper.impl.BasicPartMapper;
import com.example.document_review.repository.impl.BasicPartRepository;
import com.example.document_review.service.BasicPartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BasicPartServiceImpl implements BasicPartService {

    private final BasicPartRepository basicPartRepository;
    private final BasicPartMapper basicPartMapper;

    public BasicPartServiceImpl(BasicPartRepository basicPartRepository, BasicPartMapper basicPartMapper) {
        this.basicPartRepository = basicPartRepository;
        this.basicPartMapper = basicPartMapper;
    }
    @Override
    @Transactional
    public void save(BasicPartDto basicPartDto) {
        basicPartRepository.save(basicPartMapper.toEntity(basicPartDto));
    }
    @Override
    public List<BasicPartDto> getAll() throws Exception {
        return basicPartRepository.findAll().stream().map(entity -> basicPartMapper.toDto(entity)).collect(Collectors.toList());
    }

    @Override
    public BasicPartDto getById(int id) {
        return basicPartMapper.toDto(basicPartRepository.findById(id));
    }
}



