package com.example.document_review.mapper.impl;


import com.example.document_review.dto.AssetDto;
import com.example.document_review.entity.Asset;
import com.example.document_review.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper implements Mapper<AssetDto, Asset> {
    @Override
    public Asset toEntity(AssetDto dto) {
        return null;
    }

    @Override
    public AssetDto toDto(Asset entity) {
        return null;
    }
}
