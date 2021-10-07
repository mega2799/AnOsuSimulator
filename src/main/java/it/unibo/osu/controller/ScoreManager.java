package it.unibo.osu.controller;

import java.util.HashMap;
import java.util.Map;

import it.unibo.osu.model.GamePoints;
import it.unibo.osu.model.Score;

public class ScoreManager implements HitActionObserver{

	private Score score;

	private Map<GamePoints, Integer> statistic = new HashMap<>(){{
		put(GamePoints.MISS,0);
		put(GamePoints.OK,0);
		put(GamePoints.GREAT,0);
		put(GamePoints.PERFECT,0);
	}};
	
	public ScoreManager(Score score) {
		this.score = score;
	}
	
	public void hitted(GamePoints points) {
		int value = points.getValue();
		this.score.addPoints(value + value* this.score.getMultiplier());//ci vorrebbe anche difficulty multiplier ma magari ci guardiamo poi 
		this.score.increaseMultiplier();		
	}
	
	private void statMap(GamePoints point) {
		statistic.compute(point, (k,v)-> v+=1);
	}

	public void missed() {
		this.score.resetMultiplier();
	}
	
	public Score getScore() {
		return this.score;
	}
	
	public void setScore(Score score) {
		this.score = score;
	}
	
	public int getPoints() {
		return this.score.getPoints();
	}
	
	public int getMultiplier() {
		return this.score.getMultiplier();
	}

	public final Map<GamePoints, Integer> getStatistic() {
		return statistic;
	}

	@Override
	public void onNotify(GamePoints points) {
		statMap(points);
		switch (points) {
		case MISS:
			this.missed();
			break;
		default:
			this.hitted(points);
		}
	}
	
}
