package it.unibo.osu.view;

import it.unibo.osu.model.Hitpoint;
import it.unibo.osu.util.Mapper;
import javafx.geometry.Point2D;
import javafx.scene.Group;
public class HitCircleButtonFactory {
	private double circleSize;
	private double overallDifficulty;
	private double approachRate;
	

	public HitCircleButtonFactory(double circleSize, double difficulty, double approachRate) {
		this.circleSize = circleSize;
		this.approachRate = approachRate;
		this.overallDifficulty = difficulty;
	}
	public HitCircleButton getHitcircleView(Hitpoint hitpoint) { 
		Hitpoint mappedHitpoint = Mapper.mapHitpoint(hitpoint);
		return new HitCircleButton(new Point2D(mappedHitpoint.getX(), mappedHitpoint.getY()), this.circleSize, 0, overallDifficulty, approachRate);
	}

}
