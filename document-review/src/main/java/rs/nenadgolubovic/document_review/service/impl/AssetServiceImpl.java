package service.impl;

import dto.AssetDto;
import mapper.impl.AssetMapper;
import repository.AssetRepository;
import service.AssetService;

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
