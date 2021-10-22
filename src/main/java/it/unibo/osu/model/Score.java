package it.unibo.osu.model;

public interface Score {

	/**
	 * Sets point .
	 *
	 * @param points the new points
	 */
	void setPoints(int points);

	/**
	 * Sets the multiplier.
	 *
	 * @param multiplier the new multiplier
	 */
	void setMultiplier(int multiplier);

	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	int getPoints();

	/**
	 * Gets the multiplier.
	 *
	 * @return the multiplier
	 */
	int getMultiplier();

	/**
	 * Adds the points.
	 *
	 * @param points the points
	 */
	void addPoints(int points);

	/**
	 * Increase multiplier.
	 */
	void increaseMultiplier();

	/**
	 * Reset multiplier.
	 */
	void resetMultiplier();

	/**
	 * Gets the max multiplier.
	 *
	 * @return the max multiplier
	 */
	int getMaxMultiplier();

}