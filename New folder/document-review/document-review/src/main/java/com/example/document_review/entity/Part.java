package com.example.document_review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Part extends Asset {

    private String name;
    private String partNumber;
    private String description;
    private String serialNumber;


    public Part(Integer assetId, String assetName, String assetType, Integer cyclesSinceNew, String timeSinceNew) {
        super(assetId, assetName, assetType, cyclesSinceNew, timeSinceNew);
    }
    public Part() {

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

    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber;}

    @Override
    public String toString() {
        return "Part{" +
                "name='" + name + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", SerialNumber='" + serialNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Part part = (Part) o;
        return Objects.equals(name, part.name) && Objects.equals(partNumber, part.partNumber) && Objects.equals(description, part.description) && Objects.equals(serialNumber, part.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, partNumber, description, serialNumber);
    }
}
