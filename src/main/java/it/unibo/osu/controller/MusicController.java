package it.unibo.osu.controller;

import javafx.util.Duration;

public interface MusicController {
	public void startMusic();
	
	public void stopMusic();
	
	public Duration getDuration();
	
	public void pauseMusic();
	
	public void setOnFinished(Runnable runnable);
}