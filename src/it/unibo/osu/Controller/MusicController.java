package it.unibo.osu.Controller;

import javafx.util.Duration;

public interface MusicController {
	public void startMusic();
	
	public void stopMusic();
	
	public Duration getDuration();
	
	public void pauseMusic();
}