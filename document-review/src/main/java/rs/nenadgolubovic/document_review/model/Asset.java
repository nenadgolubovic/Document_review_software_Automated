package model;

import java.util.Objects;

public abstract class Asset {

    protected Integer assetId;
    protected String assetName;
    protected String assetType;
    protected Integer cyclesSinceNew;
    protected String timeSinceNew;

    public Asset(Integer assetId, String assetName, String assetType, Integer cyclesSinceNew, String timeSinceNew) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.assetType = assetType;
        this.cyclesSinceNew = cyclesSinceNew;
        this.timeSinceNew = timeSinceNew;
    }
    public Asset() {

    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Integer getCyclesSinceNew() {
        return cyclesSinceNew;
    }

    public void setCyclesSinceNew(Integer cyclesSinceNew) {
        this.cyclesSinceNew = cyclesSinceNew;
    }

    public String getTimeSinceNew() {
        return timeSinceNew;
    }

    public void setTimeSinceNew(String timeSinceNew) {
        this.timeSinceNew = timeSinceNew;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId=" + assetId +
                ", assetName='" + assetName + '\'' +
                ", assetType='" + assetType + '\'' +
                ", cyclesSinceNew=" + cyclesSinceNew +
                ", timeSinceNew='" + timeSinceNew + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return Objects.equals(assetId, asset.assetId) && Objects.equals(assetName, asset.assetName) && Objects.equals(assetType, asset.assetType) && Objects.equals(cyclesSinceNew, asset.cyclesSinceNew) && Objects.equals(timeSinceNew, asset.timeSinceNew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetId, assetName, assetType, cyclesSinceNew, timeSinceNew);
    }
}
