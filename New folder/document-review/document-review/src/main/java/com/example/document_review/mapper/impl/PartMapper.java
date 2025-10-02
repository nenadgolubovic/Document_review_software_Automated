package com.example.document_review.mapper.impl;

import com.example.document_review.dto.PartDto;
import com.example.document_review.entity.BasicPart;
import com.example.document_review.entity.enums.PartType;
import com.example.document_review.entity.FanBlade;
import com.example.document_review.entity.Part;
import com.example.document_review.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PartMapper implements Mapper<PartDto, Part> {
    @Override
    public Part toEntity(PartDto partDto) {

        if(partDto.getType()== PartType.BASIC){
            FanBlade part = new FanBlade();
            part.setPartId(partDto.getPartId());
            part.setName(partDto.getName());
            part.setDescription(partDto.getDescription());
            part.setType(partDto.getType());
            part.setSerialNumber(partDto.getSerialNumber());
            return part;
        }
        if(partDto.getType()==PartType.FAN_BLADE){

            BasicPart part = new BasicPart();
            part.setPartId(partDto.getPartId());
            part.setName(partDto.getName());
            part.setDescription(partDto.getDescription());
            part.setType(partDto.getType());
            part.setSerialNumber(partDto.getSerialNumber());
            return part;
        }
        return null;
    }

    @Override
    public PartDto toDto(Part part) {
        PartDto partDto = new PartDto();
        partDto.setPartId(part.getPartId());
        partDto.setName(part.getName());
        partDto.setPartNumber(part.getPartNumber());
        partDto.setDescription(part.getDescription());
        partDto.setType(part.getType());
        partDto.setSerialNumber(part.getSerialNumber());
        return partDto;
    }
}
