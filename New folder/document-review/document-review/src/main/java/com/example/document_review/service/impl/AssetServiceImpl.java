package com.example.document_review.service.impl;


import com.example.document_review.dto.AssetDto;
import com.example.document_review.mapper.impl.AssetMapper;
import com.example.document_review.repository.impl.AssetRepository;
import com.example.document_review.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository, AssetMapper assetMapper) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
    }

    @Override
    public void save(AssetDto assetDto) {
        assetRepository.save(assetMapper.toEntity(assetDto));
    }

    @Override
    public AssetDto findById(Integer assetId) {
        return null;
    }

    @Override
    public List<AssetDto> findAll() {
        return List.of();
    }
}
