package rs.nenadgolubovic.document_review.dto;

import java.util.Objects;

public class PartDto {
    private String name;
    private String partNumber;
    private String description;
    private String SerialNumber;

    public PartDto(String name, String partNumber, String description, String serialNumber) {
        this.name = name;
        this.partNumber = partNumber;
        this.description = description;
        SerialNumber = serialNumber;
    }

    public PartDto() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartDto partDto = (PartDto) o;
        return Objects.equals(name, partDto.name) && Objects.equals(partNumber, partDto.partNumber) && Objects.equals(description, partDto.description) && Objects.equals(SerialNumber, partDto.SerialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, partNumber, description, SerialNumber);
    }

    @Override
    public String toString() {
        return "PartDto{" +
                "name='" + name + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                '}';
    }
}
