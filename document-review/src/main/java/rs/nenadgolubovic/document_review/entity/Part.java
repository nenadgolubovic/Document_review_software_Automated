package rs.nenadgolubovic.document_review.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

public class Part extends Asset {

    private String name;
    private String partNumber;
    private String description;
    private String SerialNumber;


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
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Part{" +
                "name='" + name + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Part part = (Part) o;
        return Objects.equals(name, part.name) && Objects.equals(partNumber, part.partNumber) && Objects.equals(description, part.description) && Objects.equals(SerialNumber, part.SerialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, partNumber, description, SerialNumber);
    }
}
