package model;

public class Engine extends Asset{

    private Integer esn;
    private String type;
    private String model;


    public Engine(Integer assetId, String assetName, String assetType, Integer cyclesSinceNew, String timeSinceNew) {
        super(assetId, assetName, assetType, cyclesSinceNew, timeSinceNew);
    }
}
