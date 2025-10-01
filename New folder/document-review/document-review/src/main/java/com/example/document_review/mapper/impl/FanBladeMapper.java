package com.example.document_review.mapper.impl;

import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.entity.FanBlade;
import com.example.document_review.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class FanBladeMapper implements Mapper <FanBladeDto, FanBlade> {
    @Override
    public FanBlade toEntity(FanBladeDto fanBladeDto) {
        FanBlade fanBlade = new FanBlade();
        fanBlade.setName(fanBladeDto.getName());
        fanBlade.setPartNumber(fanBladeDto.getPartNumber());
        fanBlade.setDescription(fanBladeDto.getDescription());
        fanBlade.setSerialNumber(fanBladeDto.getSerialNumber());
        fanBlade.setType(fanBladeDto.getType());
        fanBlade.setCyclesSinceNew(fanBladeDto.getCyclesSinceNew());
        fanBlade.setTimeSinceNew(fanBladeDto.getTimeSinceNew());
        fanBlade.setMomentWeight(fanBladeDto.getMomentWeight());
        return fanBlade;
    }

    @Override
    public FanBladeDto toDto(FanBlade fanBlade) {
        FanBladeDto fanBladeDto = new FanBladeDto();
        fanBladeDto.setName(fanBlade.getName());
        fanBladeDto.setPartNumber(fanBlade.getPartNumber());
        fanBladeDto.setDescription(fanBlade.getDescription());
        fanBladeDto.setSerialNumber(fanBlade.getSerialNumber());
        fanBladeDto.setType(fanBlade.getType());
        fanBladeDto.setCyclesSinceNew(fanBlade.getCyclesSinceNew());
        fanBladeDto.setTimeSinceNew(fanBlade.getTimeSinceNew());
        fanBladeDto.setMomentWeight(fanBlade.getMomentWeight());
        return fanBladeDto;
    }
}
