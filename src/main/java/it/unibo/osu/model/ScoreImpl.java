package it.unibo.osu.model;

/**
 * The Class ScoreImpl implementation of {@link Score}.
 */
public class ScoreImpl implements Score {

    private int points;

    private int multiplier;

    private int maxMultiplier;

    /**
     * Constructor sets to 0 attributes of the class.
     */
    public ScoreImpl() {
        this.points = 0;
        this.multiplier = 0;
        this.maxMultiplier = 0;
    }

    /**
     * Inits the score.
     */
    public void initScore() {
        this.points = 0;
        this.multiplier = 0;
    }

    /**
     * Gets the points.
     *
     * @return the points
     */
    @Override
    public int getPoints() {
        return points;
    }

    /**
     * Gets the multiplier.
     *
     * @return the multiplier
     */
    @Override
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * Adds the points.
     *
     * @param points the points
     */
    @Override
    public void addPoints(final int points) {
        this.points += points;
    }

    /**
     * Increase multiplier.
     */
    @Override
    public void increaseMultiplier() {
        this.multiplier += 1;
        this.maxMultiplier += this.multiplier > this.maxMultiplier ? 1 : 0;
    }

    /**
     * Reset multiplier.
     */
    @Override
    public void resetMultiplier() {
        this.multiplier = 0;
    }

    /**
     * Gets the max multiplier.
     *
     * @return the max multiplier
     */
    @Override
    public final int getMaxMultiplier() {
        return maxMultiplier;
    }

}
