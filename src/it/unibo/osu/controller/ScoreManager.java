package it.unibo.osu.controller;

import it.unibo.osu.model.GamePoints;
import it.unibo.osu.model.Score;

public class ScoreManager implements HitActionObserver{

	private Score score;
	
	public ScoreManager(Score score) {
		this.score = score;
	}
	
	public void hitted(GamePoints points) {
		int value = points.getValue();
		this.score.addPoints(value + value* this.score.getMultiplier());//ci vorrebbe anche difficulty multiplier ma magari ci guardiamo poi 
		this.score.increaseMultiplier();		
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

	@Override
	public void onNotify(GamePoints points) {
		switch (points) {
		case MISS:
			this.missed();
			break;
		default:
			this.hitted(points);
		}
	}
	
}
