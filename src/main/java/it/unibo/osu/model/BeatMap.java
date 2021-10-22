package it.unibo.osu.model;

import java.util.List;

public interface BeatMap {

	List<HitpointImpl> getHitpoints();

	double getHpDrainRate();

	double getCircleSize();

	double getOverallDifficulty();

	double getApproachRate();

	String getBackground();

	String getSongName();

	List<List<Double>> getBreakTimes();

	double getStartingTime();

	String getBeatmapName();

}