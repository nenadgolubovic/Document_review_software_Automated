package com.example.document_review.service.impl;

import com.example.document_review.dto.PartDto;
import com.example.document_review.mapper.impl.PartMapper;
import com.example.document_review.repository.impl.PartRepository;
import com.example.document_review.service.PartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final PartMapper partMapper;

    public PartServiceImpl(PartRepository partRepository, PartMapper partMapper) {
        this.partRepository = partRepository;
        this.partMapper = partMapper;
    }

    @Override
    @Transactional
    public void save(PartDto partDto) {
        partRepository.save(partMapper.toEntity(partDto));

    }

    @Override
    public PartDto getById(int id) {
        return partMapper.toDto(partRepository.findById(id));
    }

    @Override
    public List<PartDto> getAll() throws Exception {
        return partRepository.findAll().stream().map(entity -> partMapper.toDto(entity)).collect(Collectors.toList());

    }
}
