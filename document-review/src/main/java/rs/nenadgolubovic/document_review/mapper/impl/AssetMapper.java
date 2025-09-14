package mapper.impl;

import dto.AssetDto;
import mapper.Mapper;
import model.Asset;

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
