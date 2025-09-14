package service;

import dto.AssetDto;

import java.util.List;

public interface AssetService {
    void save(AssetDto assetDto);
    AssetDto findById(Integer assetId);
    List<AssetDto> findAll();
}