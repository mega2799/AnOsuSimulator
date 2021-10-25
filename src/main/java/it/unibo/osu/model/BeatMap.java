package it.unibo.osu.model;

import java.util.List;

/**
 * The Interface BeatMap.
 */
public interface BeatMap {

    /**
     * Gets the {@link List} {@link HitPoint}.
     *
     * @return the hitpoints
     */
    List<HitpointImpl> getHitpoints();

    /**
     * Gets the hp drain rate.
     *
     * @return the hp drain rate
     */
    double getHpDrainRate();

    /**
     * Gets the circle size.
     *
     * @return the circle size
     */
    double getCircleSize();

    /**
     * Gets the overall difficulty.
     *
     * @return the overall difficulty
     */
    double getOverallDifficulty();

    /**
     * Gets the approach rate.
     *
     * @return the approach rate
     */
    double getApproachRate();

    /**
     * Gets the background name.
     *
     * @return the background
     */
    String getBackground();

    /**
     * Gets the song name.
     *
     * @return the song name
     */
    String getSongName();

    /**
     * Gets the break times.
     *
     * @return the break times
     */
    List<List<Double>> getBreakTimes();

    /**
     * Gets the starting time.
     *
     * @return the starting time
     */
    double getStartingTime();

    /**
     * Gets the beatmap name.
     *
     * @return the beatmap name
     */
    String getBeatmapName();

}