package it.unibo.osu.Controller;

import it.unibo.osu.Model.GamePoints;
import it.unibo.osu.Model.Score;

public class ScoreManager {

	private Score score;
	
	public ScoreManager(Score score) {
		this.score = score;
	}
	
	public void hitted(GamePoints points) {
		this.score.addPoints(points.getValue() * this.score.getMultiplier());
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
	
}
