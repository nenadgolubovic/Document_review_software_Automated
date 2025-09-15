package com.example.document_review.service.impl;


import com.example.document_review.dto.AssetDto;
import com.example.document_review.mapper.impl.AssetMapper;
import com.example.document_review.repository.impl.AssetRepository;
import com.example.document_review.service.AssetService;

import java.util.List;

public class AssetServiceImpl implements AssetService {

    public AssetServiceImpl(AssetRepository assetRepository, AssetMapper assetMapper) {
    }

    @Override
    public void save(AssetDto assetDto) {

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
