package com.example.document_review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public abstract class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partId;
    private String name;
    private String partNumber;
    private String description;
    private String serialNumber;
    private String type;
    private String cyclesSinceNew;
    private String timeSinceNew;

    public Part(String name, String partNumber, String description, String serialNumber, String type, String cyclesSinceNew, String timeSinceNew) {
        this.name = name;
        this.partNumber = partNumber;
        this.description = description;
        this.serialNumber = serialNumber;
        this.type = type;
        this.cyclesSinceNew = cyclesSinceNew;
        this.timeSinceNew = timeSinceNew;
    }

    public Part() {

    }

    @Override
    public String toString() {
        return "Part{" +
                "name='" + name + '\'' +
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
        Part part = (Part) o;
        return Objects.equals(name, part.name) && Objects.equals(partNumber, part.partNumber) && Objects.equals(description, part.description) && Objects.equals(serialNumber, part.serialNumber) && Objects.equals(type, part.type) && Objects.equals(cyclesSinceNew, part.cyclesSinceNew) && Objects.equals(timeSinceNew, part.timeSinceNew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, partNumber, description, serialNumber, type, cyclesSinceNew, timeSinceNew);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCyclesSinceNew() {
        return cyclesSinceNew;
    }

    public void setCyclesSinceNew(String cyclesSinceNew) {
        this.cyclesSinceNew = cyclesSinceNew;
    }

    public String getTimeSinceNew() {
        return timeSinceNew;
    }

    public void setTimeSinceNew(String timeSinceNew) {
        this.timeSinceNew = timeSinceNew;
    }
}


