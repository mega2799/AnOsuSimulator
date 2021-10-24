package it.unibo.osu.util;

import it.unibo.osu.model.HitPoint;
import it.unibo.osu.model.HitpointImpl;

/**
 * The Class Mapper.
 */
public final class Mapper {

    public static final double WIDTH = 1840;

    public static final double XOFFSET = 80;

    public static final double HEIGHT = 910;

    public static final double YOFFSET = 234;

    public static final double BEATMAPWIDTH = 640;

    public static final double BEATMAPHEIGHT = 480;

    private Mapper() {
    }

    /**
     * Map hitpoints position according to visual.
     *
     * @param hitpoint the hitpoint
     * @return the hit point implementation
     */
    public static HitPoint mapHitpoint(final HitPoint hitpoint) {
        double x = hitpoint.getX();
        double y = hitpoint.getY();
        double newX = (x * (WIDTH - XOFFSET) / BEATMAPWIDTH) + XOFFSET;
        double newY = (y * (HEIGHT - YOFFSET) / BEATMAPHEIGHT) + YOFFSET;
        return new HitpointImpl(newX, newY, hitpoint.getTime());
    }
}
