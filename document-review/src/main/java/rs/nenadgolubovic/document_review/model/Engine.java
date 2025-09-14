package model;

import java.util.Objects;

public class Engine extends Asset{

    private Integer esn;
    private String type;
    private String model;


    public Engine(Integer assetId, String assetName, String assetType, Integer cyclesSinceNew, String timeSinceNew) {
        super(assetId, assetName, assetType, cyclesSinceNew, timeSinceNew);
    }

    public Engine() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Engine engine = (Engine) o;
        return Objects.equals(esn, engine.esn) && Objects.equals(type, engine.type) && Objects.equals(model, engine.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), esn, type, model);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "esn=" + esn +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                '}';
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
