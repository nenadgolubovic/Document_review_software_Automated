package com.example.document_review.service.impl;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.exception.EntityNotFoundException;
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
    @Transactional
    public void delete(Integer partId) throws EntityNotFoundException {
        basicPartRepository.delete(partId);
    }

    @Override
    public List<BasicPartDto> getAll() throws EntityNotFoundException {
        return basicPartRepository.findAll().stream().map(basicPartMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BasicPartDto getById(Integer id) {
        return basicPartMapper.toDto(basicPartRepository.findById(id));
    }

    @Override
    public void update(Integer basicPartId) {
        // This method is intentionally left empty for now.
    }

}



