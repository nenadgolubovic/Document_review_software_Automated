package model;

public class Part extends Asset {

    private String name;
    private String partNumber;
    private String description;
    private String SerialNumber;


    public Part(Integer assetId, String assetName, String assetType, Integer cyclesSinceNew, String timeSinceNew) {
        super(assetId, assetName, assetType, cyclesSinceNew, timeSinceNew);
    }
}
