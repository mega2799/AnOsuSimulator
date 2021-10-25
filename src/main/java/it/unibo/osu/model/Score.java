package it.unibo.osu.model;

public interface Score {

    /**
     * Gets the points.
     *
     * @return the points
     */
    int getPoints();

    /**
     * Gets the multiplier.
     *
     * @return the multiplier
     */
    int getMultiplier();

    /**
     * Adds points.
     *
     * @param points the points
     */
    void addPoints(int points);

    /**
     * Increase multiplier.
     */
    void increaseMultiplier();

    /**
     * Reset multiplier.
     */
    void resetMultiplier();

    /**
     * Gets the max multiplier.
     *
     * @return the max multiplier
     */
    int getMaxMultiplier();

}