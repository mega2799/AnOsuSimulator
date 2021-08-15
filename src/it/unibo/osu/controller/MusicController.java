package it.unibo.osu.controller;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public interface MusicController {
	void startMusic();
	
	void stopMusic();
	
	Duration getDuration();
	
	void pauseMusic();
	
	MediaPlayer getAudio();	
}