package it.unibo.osu.model;

public class Score {

	private int points;

	private int multiplier;
	
	private int maxMultiplier;

	public Score() {
		this.points = 0;
		this.multiplier = 0;
		this.maxMultiplier = 0;
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
		this.maxMultiplier += this.multiplier > this.maxMultiplier ? 1 : 0;
	}
	
	public void resetMultiplier() {
		this.multiplier = 0;
	}

	public final int getMaxMultiplier() {
		return maxMultiplier;
	}

}
