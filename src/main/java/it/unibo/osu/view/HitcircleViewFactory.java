package it.unibo.osu.view;

import it.unibo.osu.model.HitPoint;
import it.unibo.osu.util.Mapper;

/**
 * A factory for creating HitcircleView objects.
 */
public class HitcircleViewFactory {
	
	private String innerCircleUrl;
	
	private String outerCircleUrl;
	
	private double circleSize;
	
	private double overallDifficulty;
	
	private double approachRate;
	
	/**
	 * Instantiates a new hitcircle view factory.
	 *
	 * @param innerCircleUrl the inner circle url
	 * @param outerCircleUrl the outer circle url
	 * @param circleSize the circle size
	 * @param difficulty the difficulty
	 * @param approachRate the approach rate
	 */
	public HitcircleViewFactory(String innerCircleUrl, String outerCircleUrl, double circleSize, double difficulty, double approachRate) {
		this.innerCircleUrl = innerCircleUrl;
		this.outerCircleUrl = outerCircleUrl;
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
	public HitcircleViewImpl getHitcircleView(HitPoint hitpoint) { 
		HitPoint mappedHitpoint = Mapper.mapHitpoint(hitpoint);
		return new HitcircleViewImpl(this.innerCircleUrl, this.outerCircleUrl, this.circleSize, this.overallDifficulty, this.approachRate, mappedHitpoint.getX(), mappedHitpoint.getY());
	}
}
