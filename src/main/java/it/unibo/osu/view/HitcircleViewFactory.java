package it.unibo.osu.view;

import it.unibo.osu.model.HitPoint;
import it.unibo.osu.util.Mapper;
public class HitcircleViewFactory {
	private String innerCircleUrl;
	private String outerCircleUrl;
	private double circleSize;
	private double overallDifficulty;
	private double approachRate;
	
	public HitcircleViewFactory(String innerCircleUrl, String outerCircleUrl, double circleSize, double difficulty, double approachRate) {
		this.innerCircleUrl = innerCircleUrl;
		this.outerCircleUrl = outerCircleUrl;
		this.circleSize = circleSize;
		this.approachRate = approachRate;
		this.overallDifficulty = difficulty;
	}
	public HitcircleViewImpl getHitcircleView(HitPoint hitpoint) { 
		HitPoint mappedHitpoint = Mapper.mapHitpoint(hitpoint);
		return new HitcircleViewImpl(this.innerCircleUrl, this.outerCircleUrl, this.circleSize, this.overallDifficulty, this.approachRate, mappedHitpoint.getX(), mappedHitpoint.getY());
	}
}
