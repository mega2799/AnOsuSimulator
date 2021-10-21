package it.unibo.osu.controller;

import java.util.HashMap;
import java.util.Map;

import it.unibo.osu.model.GamePoints;
import it.unibo.osu.model.HitPoint;
import it.unibo.osu.model.Score;

public class ScoreManagerImpl {

	private Score score;
	
	private Map<GamePoints, Integer> statistic = new HashMap<>(){{
		put(GamePoints.MISS,0);
		put(GamePoints.OK,0);
		put(GamePoints.GREAT,0);
		put(GamePoints.PERFECT,0);
	}};
	
	/**
	 * Instantiates a new score manager implementation setting {@link Score} to manage Uso! score.
	 *
	 * @param score the score
	 */
	public ScoreManagerImpl(Score score) {
		this.score = score;
	}
	
	/**
	 * Hit is the function that is called when the user hit a {@link HitPoint}.
	 *
	 * @param points the points
	 */
	public void hit(GamePoints points) {
		int value = points.getValue();
		this.score.addPoints(value + value* this.score.getMultiplier());//ci vorrebbe anche difficulty multiplier ma magari ci guardiamo poi 
		this.score.increaseMultiplier();	
		statMap(points);
	}
	
	private void statMap(GamePoints point) {
		statistic.compute(point, (k,v)-> v+=1);
	}

	/**
	 * missed is the function that is called when the user miss a {@link HitPoint}.
	 */
	public void missed() {
		statMap(GamePoints.MISS);
		this.score.resetMultiplier();
	}
	
	/**
	 * @return the {@link Score}
	 */
	public Score getScore() {
		return this.score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(Score score) {
		this.score = score;
	}

	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints() {
		return this.score.getPoints();
	}
	
	/**
	 * Gets the multiplier.
	 *
	 * @return the multiplier
	 */
	public int getMultiplier() {
		return this.score.getMultiplier();
	}

	/**
	 * Gets the statistic.
	 *
	 * @return the statistic
	 */
	public final Map<GamePoints, Integer> getStatistic() {
		return statistic;
	}

//	@Override
//	public void onNotify(GamePoints points) {
//		statMap(points);
//		switch (points) {
//		case MISS:
//			this.missed();
//			break;
//		default:
//			this.hitted(points);
//		}
//	}
	
}
