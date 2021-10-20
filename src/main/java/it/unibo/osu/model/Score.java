package it.unibo.osu.model;

public interface Score {

	void setPoints(int points);

	void setMultiplier(int multiplier);

	int getPoints();

	int getMultiplier();

	void addPoints(int points);

	void increaseMultiplier();

	void resetMultiplier();

	int getMaxMultiplier();

}