package it.unibo.osu.controller;

import javafx.util.Duration;

public interface MusicController {
	void startMusic();
	
	void stopMusic();
	
	Duration getDuration(); //da eliminare
	
	void pauseMusic();
	
	void setOnFinished(Runnable runnable);
}