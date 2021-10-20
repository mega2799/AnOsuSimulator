package it.unibo.osu.util;

import it.unibo.osu.model.HitPoint;
import it.unibo.osu.model.HitpointImpl;

public class Mapper {
	public final static double WIDTH = 1840;
	public final static double XOFFSET = 80;
	public final static double HEIGHT = 910;
	public final static double YOFFSET = 234;
	
	public final static double BEATMAPWIDTH = 640;
	public final static double BEATMAPHEIGHT = 480;
	
	public static HitPoint mapHitpoint(HitPoint hitpoint) {
		double x = hitpoint.getX();
		double y = hitpoint.getY();
		double newX = (x  * (WIDTH - XOFFSET) / BEATMAPWIDTH) + XOFFSET;
		double newY = (y  * (HEIGHT - YOFFSET) / BEATMAPHEIGHT) + YOFFSET;
		return new HitpointImpl(newX, newY, hitpoint.getTime());
	}
}
