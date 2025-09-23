package com.example.document_review.mapper.impl;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.entity.BasicPart;
import com.example.document_review.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BasicPartMapper implements Mapper<BasicPartDto, BasicPart> {

    @Override
    public BasicPart toEntity(BasicPartDto basicPartDto) {
        BasicPart basicPart = new BasicPart();
        basicPart.setName(basicPartDto.getName());
        basicPart.setPartNumber(basicPartDto.getPartNumber());
        basicPart.setDescription(basicPartDto.getDescription());
        basicPart.setSerialNumber(basicPartDto.getSerialNumber());
        basicPart.setType(basicPartDto.getType());
        basicPart.setCyclesSinceNew(basicPartDto.getCyclesSinceNew());
        basicPart.setTimeSinceNew(basicPartDto.getTimeSinceNew());
        return basicPart;
    }

    @Override
    public BasicPartDto toDto(BasicPart basicPart) {
        BasicPartDto basicPartDto = new BasicPartDto();
        basicPartDto.setName(basicPart.getName());
        basicPartDto.setPartNumber(basicPart.getPartNumber());
        basicPartDto.setDescription(basicPart.getDescription());
        basicPartDto.setSerialNumber(basicPart.getSerialNumber());
        basicPartDto.setType(basicPart.getType());
        basicPartDto.setCyclesSinceNew(basicPart.getCyclesSinceNew());
        basicPartDto.setTimeSinceNew(basicPart.getTimeSinceNew());
        return basicPartDto;
    }


}
