package it.unibo.osu.model;

public enum GamePoints {
    MISS(0),
    OK(50),
    GREAT(100),
    PERFECT(300);

    private int value;

    GamePoints(final int value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }
}
