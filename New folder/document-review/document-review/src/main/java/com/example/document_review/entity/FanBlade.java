package com.example.document_review.entity;


import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class FanBlade extends Part {
    public FanBlade(String name, String partNumber, String description, String serialNumber, String type, String cyclesSinceNew, String timeSinceNew, String momentWeight) {
        super(name, partNumber, description, serialNumber, type, cyclesSinceNew, timeSinceNew);
        this.momentWeight = momentWeight;
    }

    public FanBlade(String momentWeight) {
        this.momentWeight = momentWeight;
    }

    private String momentWeight;

    public FanBlade() {

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
        FanBlade fanBlade = (FanBlade) o;
        return Objects.equals(momentWeight, fanBlade.momentWeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), momentWeight);
    }

    @Override
    public String toString() {
        return "FanBlade{" +
                "momentWeight='" + momentWeight + '\'' +
                '}';
    }
}
