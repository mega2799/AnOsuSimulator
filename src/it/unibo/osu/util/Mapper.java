package it.unibo.osu.util;

import it.unibo.osu.model.Hitpoint;

public class Mapper {
	public final static double WIDTH = 1920;
	public final static double HEIGHT = 1080;
	public final static double BEATMAPWIDTH = 640;
	public final static double BEATMAPHEIGHT = 480;
	
	public static Hitpoint mapHitpoint(Hitpoint hitpoint) {
		double x = hitpoint.getX();
		double y = hitpoint.getY();
		double newX = (x / Mapper.BEATMAPWIDTH) * Mapper.WIDTH;
		double newY = (y / Mapper.BEATMAPHEIGHT) * Mapper.HEIGHT;
		return new Hitpoint(newX, newY, hitpoint.getTime());
	}
}
