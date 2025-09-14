package dto;

import java.util.Objects;

public class EngineDto {

    private Integer esn;
    private String type;
    private String model;

    public EngineDto(Integer esn, String type, String model) {
        this.esn = esn;
        this.type = type;
        this.model = model;
    }

    public EngineDto() {
    }

    @Override
    public String toString() {
        return "EngineDto{" +
                "esn=" + esn +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngineDto engineDto = (EngineDto) o;
        return Objects.equals(esn, engineDto.esn) && Objects.equals(type, engineDto.type) && Objects.equals(model, engineDto.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(esn, type, model);
    }

    public Integer getEsn() {
        return esn;
    }

    public void setEsn(Integer esn) {
        this.esn = esn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
