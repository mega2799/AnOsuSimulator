package it.unibo.osu.model;

public interface LifeBar {

	double MAXHP = 100;

	/**
	 * Decrease player life.
	 */
	void lostLife();

	/**
	 * Increase player life.
	 *
	 * @param gamePoints the game points
	 */
	void gainLife(GamePoints gamePoints);

	/**
	 * Removes life from the player when the song is playing.
	 */
	void drain();

	/**
	 * Gets current hp.
	 *
	 * @return the hp
	 */
	double getHp();

	/**
	 * Update.
	 */
	void update();

}