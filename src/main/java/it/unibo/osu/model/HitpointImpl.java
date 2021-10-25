package it.unibo.osu.model;

/**
 * The Class HitpointImpl implementation of {@link HitPoint}.
 */
public class HitpointImpl implements HitPoint {
    private Pair<Double, Double> position;

    private double time;

    /**
     * Instantiates a new hitpoint impl.
     *
    // * @param position the position
     * @param time     the time
     * @param pair      the pair of coordinates
     */
    public HitpointImpl(final Pair<Double, Double> pair, final double time) {
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
        this.position = new Pair<Double, Double>(x, y);
        this.time = time;
    }

    @Override
    public void setPosition(final Pair<Double, Double> position) {
        this.position = position;
    }

    @Override
    public void setTime(final double time) {
        this.time = time;
    }

    @Override
    public Pair<Double, Double> getPosition() {
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
        this.position = new Pair<Double, Double>(x, this.position.getY());
    }

    @Override
    public void setY(final double y) {
        this.position = new Pair<Double, Double>(this.position.getX(), y);
    }

    @Override
    public String toString() {
        return "[" + this.position + ", time " + this.time + "]";
    }
}
