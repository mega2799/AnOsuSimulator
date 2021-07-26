package it.unibo.osu.util;

public enum BeatmapOptions {
	
	HITOBJECTS("[HitObjects]");
	
	private String value;
	private BeatmapOptions(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}
}
