package com.example.document_review.dto;

import com.example.document_review.entity.Enums.PartType;

import java.util.Objects;



public class FanBladeDto extends PartDto{



    private String momentWeight;

    public FanBladeDto() {

    }

    public FanBladeDto(Integer partId, String name, String partNumber, String description, String serialNumber, PartType type, String cyclesSinceNew, String timeSinceNew, String momentWeight) {
        super(partId, name, partNumber, description, serialNumber, type, cyclesSinceNew, timeSinceNew);
        this.momentWeight = momentWeight;
    }

    public String getMomentWeight() {
        return momentWeight;
    }

    public void setMomentWeight(String momentWeight) {
        this.momentWeight = momentWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FanBladeDto that = (FanBladeDto) o;
        return Objects.equals(momentWeight, that.momentWeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), momentWeight);
    }

    @Override
    public String toString() {
        return "FanBladeDto{" +
                "momentWeight='" + momentWeight + '\'' +
                '}';
    }
}
