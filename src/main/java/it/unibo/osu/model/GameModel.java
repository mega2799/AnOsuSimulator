package it.unibo.osu.model;

import java.util.List;

import it.unibo.osu.controller.ScoreManagerImpl;
import it.unibo.osu.util.Clock;
import it.unibo.osu.view.HitcircleView;

public interface GameModel {

	/**
	 * Inits the game on start.
	 */
	void initGameOnStart();

	/**
	 * Update game controller.
	 *
	 * @param t the t
	 */
	void update(double t);

	/**
	 * Sets the pause.
	 */
	void setPause();

	/**
	 * Button missed.
	 */
	void buttonMissed();

	/**
	 * Button hitted.
	 *
	 * @param gamePoints the game points associated with the {@link HitPoint}
	 */
	void buttonHitted(GamePoints gamePoints);

	/**
	 * Gets the {@link GameStatus}.
	 *
	 * @return the status
	 */
	GameStatus getStatus();

	/**
	 * Gets the {@link BeatMap}.
	 *
	 * @return the beat map
	 */
	BeatMap getBeatMap();

	/**
	 * Gets the {@link ScoreManager}.
	 *
	 * @return the score manager
	 */
	ScoreManagerImpl getScoreManager();

	/**
	 * Gets the current {@link HitPoint}.
	 *
	 * @return the current hitbuttons
	 */
	List<HitpointImpl> getCurrentHitbuttons();

	/**
	 * Gets the osu {@link Clock}.
	 *
	 * @return the osu clock
	 */
	Clock getOsuClock();

	/**
	 * Clear current hitbuttons.
	 *
	 * @param currentHitbuttons the current hitbuttons
	 */
	void clearCurrentHitbuttons(List<HitpointImpl> currentHitbuttons);

	/**
	 * Checks if is game over.
	 *
	 * @return true, if is game over
	 */
	boolean isGameOver();

	/**
	 * Gets the {@link LifeBar}.
	 *
	 * @return the life bar
	 */
	LifeBar getLifeBar();

}