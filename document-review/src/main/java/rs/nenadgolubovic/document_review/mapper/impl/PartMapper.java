package mapper.impl;

import dto.PartDto;
import mapper.Mapper;
import model.Part;

public class PartMapper implements Mapper<PartDto, Part> {
    @Override
    public Part toEntity(PartDto partDto) {
        return null;
    }

    @Override
    public PartDto toDto(Part part) {
        return null;
    }
}
