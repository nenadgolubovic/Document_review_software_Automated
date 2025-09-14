package dto;

import java.io.Serializable;
import java.util.Objects;

public class AssetDto implements Serializable {

    protected Integer assetId;
    protected String assetName;
    protected String assetType;
    protected Integer cyclesSinceNew;
    protected String timeSinceNew;

    public AssetDto(Integer assetId, String assetName, String assetType, Integer cyclesSinceNew, String timeSinceNew) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.assetType = assetType;
        this.cyclesSinceNew = cyclesSinceNew;
        this.timeSinceNew = timeSinceNew;
    }

    public AssetDto() {
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getTimeSinceNew() {
        return timeSinceNew;
    }

    public void setTimeSinceNew(String timeSinceNew) {
        this.timeSinceNew = timeSinceNew;
    }

    public Integer getCyclesSinceNew() {
        return cyclesSinceNew;
    }

    public void setCyclesSinceNew(Integer cyclesSinceNew) {
        this.cyclesSinceNew = cyclesSinceNew;
    }

    @Override
    public String toString() {
        return "AssetDto{" +
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
        AssetDto assetDto = (AssetDto) o;
        return Objects.equals(assetId, assetDto.assetId) && Objects.equals(assetName, assetDto.assetName) && Objects.equals(assetType, assetDto.assetType) && Objects.equals(cyclesSinceNew, assetDto.cyclesSinceNew) && Objects.equals(timeSinceNew, assetDto.timeSinceNew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetId, assetName, assetType, cyclesSinceNew, timeSinceNew);
    }
}
