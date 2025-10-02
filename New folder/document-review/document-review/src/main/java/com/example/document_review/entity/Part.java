package com.example.document_review.entity;

import com.example.document_review.entity.enums.PartType;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuperBuilder
@Entity
public abstract class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partId;
    private String name;
    private String partNumber;
    private String description;
    private String serialNumber;
    private PartType type;
    private String cyclesSinceNew;
    private String timeSinceNew;
    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();


    protected Part(Integer partId, String name, String partNumber, String description, String serialNumber, PartType type, String cyclesSinceNew, String timeSinceNew, List<Document> documents) {
        this.partId = partId;
        this.name = name;
        this.partNumber = partNumber;
        this.description = description;
        this.serialNumber = serialNumber;
        this.type = type;
        this.cyclesSinceNew = cyclesSinceNew;
        this.timeSinceNew = timeSinceNew;
        this.documents = documents;
    }


    protected Part() {

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

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(partId, part.partId) && Objects.equals(name, part.name) && Objects.equals(partNumber, part.partNumber) && Objects.equals(description, part.description) && Objects.equals(serialNumber, part.serialNumber) && Objects.equals(type, part.type) && Objects.equals(cyclesSinceNew, part.cyclesSinceNew) && Objects.equals(timeSinceNew, part.timeSinceNew) && Objects.equals(documents, part.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId, name, partNumber, description, serialNumber, type, cyclesSinceNew, timeSinceNew, documents);
    }

    @Override
    public String toString() {
        return "Part{" +
                "partId=" + partId +
                ", name='" + name + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", type='" + type + '\'' +
                ", cyclesSinceNew='" + cyclesSinceNew + '\'' +
                ", timeSinceNew='" + timeSinceNew + '\'' +
                ", documentsCount=" + (documents != null ? documents.size() : 0) +
                '}';
    }
}


