package it.unibo.osu.model;

/**
 * The Interface HitPoint.
 */
public interface HitPoint {

    /**
     * Sets the {@link Pair}.
     *
     * @param pair the new position
     */
    void setPosition(Pair<Double, Double> pair);

    /**
     * Sets the time.
     *
     * @param time the new time
     */
    void setTime(double time);

    /**
     * Gets the {@link Pair}.
     *
     * @return the position
     */
    Pair<Double, Double> getPosition();
    /**
     * Gets the time.
     *
     * @return the time
     */
    double getTime();

    /**
     * Gets the x axis position.
     *
     * @return the x
     */
    double getX();

    /**
     * Gets the y axis position.
     *
     * @return the y
     */
    double getY();

    /**
     * Sets the x axis position.
     *
     * @param x the new x
     */
    void setX(double x);

    /**
     * Sets the y axis position.
     *
     * @param y the new y
     */
    void setY(double y);

}