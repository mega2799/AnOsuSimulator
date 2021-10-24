package it.unibo.osu.util;

/**
 * The Enum BeatmapOptions.
 */
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

    BeatmapOptions(final String value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }
}
