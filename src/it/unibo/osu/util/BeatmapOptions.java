package it.unibo.osu.util;

public enum BeatmapOptions {
	
	HITOBJECTS("[HitObjects]"),
	GENERAL("[General]"),
	EDITOR("[Editor]"),
	METADATA("[Metadata]"),
	DIFFICULTY("[Difficulty]"),
	EVENTS("[Events]"),
	TIMINGPOINTS("[TimingPoints]"),
	COLOURS("[Colours]");
	
	private String value;
	private BeatmapOptions(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}
}
