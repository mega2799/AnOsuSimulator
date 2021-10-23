package it.unibo.osu.model;

import javafx.geometry.Point2D;

/**
 * The Class HitpointImpl implementation of {@link HitPoint}.
 */
public class HitpointImpl implements HitPoint {
    private Point2D position;
    private double time;

    /**
     * Instantiates a new hitpoint impl.
     *
     * @param position the position
     * @param time     the time
     */
    public HitpointImpl(final Point2D position, final double time) {
        this.position = position;
        this.time = time;
    }

    /**
     * Instantiates a new hitpoint impl.
     *
     * @param x    the x
     * @param y    the y
     * @param time the time
     */
    public HitpointImpl(final double x, final double y, final double time) {
        this.position = new Point2D(x, y);
        this.time = time;
    }

    @Override
    public void setPosition(final Point2D position) {
        this.position = position;
    }

    @Override
    public void setTime(final double time) {
        this.time = time;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public double getX() {
        return this.position.getX();
    }

    @Override
    public double getY() {
        return this.position.getY();
    }

    @Override
    public void setX(final double x) {
        this.position = new Point2D(x, this.position.getY());
    }

    @Override
    public void setY(final double y) {
        this.position = new Point2D(this.position.getX(), y);
    }

    @Override
    public String toString() {
        return "[" + this.position + ", time " + this.time + "]";
    }
}
