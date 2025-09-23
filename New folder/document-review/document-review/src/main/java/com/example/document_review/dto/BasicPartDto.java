package com.example.document_review.dto;

public class BasicPartDto extends PartDto {
    public BasicPartDto(Integer partId, String name, String partNumber, String description, String serialNumber, String type, String cyclesSinceNew, String timeSinceNew) {
        super(partId, name, partNumber, description, serialNumber, type, cyclesSinceNew, timeSinceNew);
    }

    public BasicPartDto() {
        super();
    }
}
