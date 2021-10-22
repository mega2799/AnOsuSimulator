package it.unibo.osu.model;

public class ScoreImpl implements Score {

	private int points;

	private int multiplier;
	
	private int maxMultiplier;

	public ScoreImpl() {
		this.points = 0;
		this.multiplier = 0;
		this.maxMultiplier = 0;
	}
	
	public void initScore() {
		this.points = 0;
		this.multiplier = 0;
	}
	
	/*
	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	*/
	
	@Override
	public int getPoints() {
		return points;
	}


	@Override
	public int getMultiplier() {
		return multiplier;
	}
	
	@Override
	public void addPoints(int points) {
		this.points += points;
	}
	
	@Override
	public void increaseMultiplier() {
		this.multiplier += 1;
		this.maxMultiplier += this.multiplier > this.maxMultiplier ? 1 : 0;
	}
	
	@Override
	public void resetMultiplier() {
		this.multiplier = 0;
	}

	@Override
	public final int getMaxMultiplier() {
		return maxMultiplier;
	}

}
