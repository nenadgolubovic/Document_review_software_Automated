package com.example.document_review.entity;


import com.example.document_review.entity.enums.PartType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;

@SuperBuilder
@Entity
public class FanBlade extends Part {
    public FanBlade(Integer partId , String name, String partNumber, String description, String serialNumber, PartType type, String cyclesSinceNew, String timeSinceNew, String momentWeight, List<Document> documents) {
        super(partId, name, partNumber, description, serialNumber, type, cyclesSinceNew, timeSinceNew, documents);
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
