package it.unibo.osu.model;

import java.util.List;

import it.unibo.osu.controller.ScoreManagerImpl;
import it.unibo.osu.util.Clock;

public interface GameModel {

	void initGameOnStart();

	void update(double t);

	void setPause();

	void buttonMissed();

	void buttonHitted(GamePoints gamePoints);

	GameStatus getStatus();

	BeatMap getBeatMap();

	ScoreManagerImpl getScoreManager();

	List<HitpointImpl> getCurrentHitbuttons();

	Clock getOsuClock();

	void clearCurrentHitbuttons(List<HitpointImpl> currentHitbuttons);

	//da implementare
	boolean isGameOver();

	LifeBar getLifeBar();

}