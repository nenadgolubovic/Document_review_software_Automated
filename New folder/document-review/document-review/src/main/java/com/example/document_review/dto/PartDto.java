package com.example.document_review.dto;

import com.example.document_review.entity.enums.PartType;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class PartDto {


    private Integer partId;
    private String name;
    private String partNumber;
    private String description;
    private String serialNumber;
    private PartType type;

    public PartDto() {

    }

    public String getCyclesSinceNew() {
        return cyclesSinceNew;
    }

    public void setCyclesSinceNew(String cyclesSinceNew) {
        this.cyclesSinceNew = cyclesSinceNew;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public PartType getType() {
        return type;
    }

    public void setType(PartType type) {
        this.type = type;
    }

    public String getTimeSinceNew() {
        return timeSinceNew;
    }

    public void setTimeSinceNew(String timeSinceNew) {
        this.timeSinceNew = timeSinceNew;
    }

    private String cyclesSinceNew;
    private String timeSinceNew;


    @Override
    public String
    toString() {
        return "PartDto{" +
                "partId=" + partId +
                ", name='" + name + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", type='" + type + '\'' +
                ", cyclesSinceNew='" + cyclesSinceNew + '\'' +
                ", timeSinceNew='" + timeSinceNew + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartDto partDto = (PartDto) o;
        return Objects.equals(partId, partDto.partId) && Objects.equals(name, partDto.name) && Objects.equals(partNumber, partDto.partNumber) && Objects.equals(description, partDto.description) && Objects.equals(serialNumber, partDto.serialNumber) && Objects.equals(type, partDto.type) && Objects.equals(cyclesSinceNew, partDto.cyclesSinceNew) && Objects.equals(timeSinceNew, partDto.timeSinceNew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId, name, partNumber, description, serialNumber, type, cyclesSinceNew, timeSinceNew);
    }
}


