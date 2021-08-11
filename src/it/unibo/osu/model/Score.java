package it.unibo.osu.model;

public class Score {

	private int points;
	private int multiplier;
	
	public Score() {
		this.points = 0;
		this.multiplier = 1;
	}
	
	public void initScore() {
		this.points = 0;
		this.multiplier = 0;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	public int getPoints() {
		return points;
	}

	public int getMultiplier() {
		return multiplier;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public void increaseMultiplier() {
		this.multiplier += 1;
	}
	
	public void resetMultiplier() {
		this.multiplier = 0;
	}
}
