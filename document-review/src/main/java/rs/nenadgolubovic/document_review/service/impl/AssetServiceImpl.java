package rs.nenadgolubovic.document_review.service.impl;


import rs.nenadgolubovic.document_review.dto.AssetDto;
import rs.nenadgolubovic.document_review.mapper.impl.AssetMapper;
import rs.nenadgolubovic.document_review.repository.impl.AssetRepository;
import rs.nenadgolubovic.document_review.service.AssetService;

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
