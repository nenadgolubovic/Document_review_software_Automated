package com.example.document_review.dto;

import com.example.document_review.entity.Enums.PartType;
import lombok.Builder;



public class BasicPartDto extends PartDto {

    @Builder
    public BasicPartDto(Integer partId, String name, String partNumber, String description, String serialNumber, PartType type, String cyclesSinceNew, String timeSinceNew) {
        super(partId, name, partNumber, description, serialNumber, type, cyclesSinceNew, timeSinceNew);
    }

    public BasicPartDto() {
        super();
    }
}
