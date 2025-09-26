package com.example.document_review.mapper.impl;


import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.dto.PartDto;
import com.example.document_review.entity.Part;
import com.example.document_review.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PartMapper implements Mapper<PartDto, Part> {
    @Override
    public Part toEntity(PartDto partDto) {
        return null;
    }

    @Override
    public PartDto toDto(Part part) {
        PartDto partDto = new PartDto();
        partDto.setName(part.getName());
        partDto.setPartNumber(part.getPartNumber());
        partDto.setDescription(part.getDescription());
        partDto.setType(part.getType());
        partDto.setSerialNumber(part.getSerialNumber());
        return partDto;
    }
}
