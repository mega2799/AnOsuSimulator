package it.unibo.osu.model;

import it.unibo.osu.util.BeatmapOptions;
import it.unibo.osu.util.BeatmapReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The Class BeatMapImpl implementation of {@link BeatMap}.
 */
public class BeatMapImpl implements BeatMap {

    /* Mandatory entities for each BeatMap file */
    private List<HitpointImpl> hitpoints;

    private double hpDrainRate;

    private double circleSize;

    private double overallDifficulty;

    private double approachRate;

    private String background;

    private String songName;

    private List<List<Double>> breakTimes;

    private double startingTime;

    private String beatmapName;

    /**
     * Instantiates a new beat map.
     *
     * @param fileName is the file name
     */
    public BeatMapImpl(final String fileName) {
        try (BeatmapReader reader = new BeatmapReader(
                this.getClass().getResourceAsStream(fileName))) {
            final Map<String, String> map = reader
                    .getOptionMap(BeatmapOptions.DIFFICULTY);
            this.circleSize = Double.parseDouble(map.get("CircleSize"));
            this.overallDifficulty = Double
                    .parseDouble(map.get("OverallDifficulty"));
            this.approachRate = Double.parseDouble(map.get("ApproachRate"));
            this.hpDrainRate = Double.parseDouble(map.get("HPDrainRate"));
            this.hitpoints = reader.getHitpoints();
            this.background = reader.getBakground();
            this.songName = reader.getOptionMap(BeatmapOptions.GENERAL)
                    .get("AudioFilename");
            this.breakTimes = reader.getBreakTimes();
            this.startingTime = reader.getStartingTime();
            this.beatmapName = reader.getOptionMap(BeatmapOptions.METADATA)
                    .get("Title");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<HitpointImpl> getHitpoints() {
        return hitpoints;
    }

    @Override
    public double getHpDrainRate() {
        return hpDrainRate;
    }

    @Override
    public double getCircleSize() {
        return circleSize;
    }

    @Override
    public double getOverallDifficulty() {
        return overallDifficulty;
    }

    @Override
    public double getApproachRate() {
        return approachRate;
    }

    @Override
    public String getBackground() {
        return this.background;
    }

    @Override
    public String getSongName() {
        return songName;
    }

    @Override
    public List<List<Double>> getBreakTimes() {
        return breakTimes;
    }

    @Override
    public double getStartingTime() {
        return startingTime;
    }

    @Override
    public String getBeatmapName() {
        return beatmapName;
    }

}
