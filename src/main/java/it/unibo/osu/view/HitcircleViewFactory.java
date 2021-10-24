package it.unibo.osu.view;

import it.unibo.osu.model.HitPoint;
import it.unibo.osu.util.Mapper;

/**
 * A factory for creating HitcircleView objects.
 */
public class HitcircleViewFactory {

    private double circleSize;

    private double overallDifficulty;

    private double approachRate;

    /**
     * Instantiates a new hitcircle view factory.
     *
     * @param circleSize   the circle size
     * @param difficulty   the difficulty
     * @param approachRate the approach rate
     */
    public HitcircleViewFactory(final double circleSize, final double difficulty, final double approachRate) {
        this.circleSize = circleSize;
        this.approachRate = approachRate;
        this.overallDifficulty = difficulty;
    }

    /**
     * Gets the hitcircle view.
     *
     * @param hitpoint the hitpoint
     * @return the hitcircle view
     */
    public HitcircleViewImpl getHitcircleView(final HitPoint hitpoint) {
        final HitPoint mappedHitpoint = Mapper.mapHitpoint(hitpoint);
        return new HitcircleViewImpl(this.circleSize, this.overallDifficulty, this.approachRate, mappedHitpoint.getX(),
                mappedHitpoint.getY());
    }
}
