package com.example.document_review.service;


import com.example.document_review.dto.AssetDto;

import java.util.List;

public interface AssetService {
    void save(AssetDto assetDto);
    AssetDto findById(Integer assetId);
    List<AssetDto> findAll();
}