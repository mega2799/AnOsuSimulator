package it.unibo.osu.controller;

//import java.io.File;
import java.net.URISyntaxException;

import it.unibo.osu.model.GameModel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
public class MusicControllerImpl extends AbstractSubject implements MusicController {

	private MediaPlayer audioMedia;

	private GameModel game;
	/*
	 * Volume range is (0, 1)
	 */
	private double level = 1;
	
	private final static MusicControllerImpl AUDIOPLAYER = new MusicControllerImpl();
	
	
	public static MusicControllerImpl getAudio() { //anche final ????
		return AUDIOPLAYER;
	}
	
	public void setVolume(double level) {
		this.level = level;
		AUDIOPLAYER.audioMedia.setVolume(level);
	}
	
	public void setSong(String name) {
		try {
			if(AUDIOPLAYER.audioMedia != null) {
				AUDIOPLAYER.stopMusic();
			}
			AUDIOPLAYER.audioMedia = new MediaPlayer(new Media(this.getClass().getResource(name).toURI().toString()));
			this.setVolume(this.level);
			AUDIOPLAYER.startMusic();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MusicControllerImpl() {
	}

	public MusicControllerImpl(final String name) {
		//this.audioMedia = new MediaPlayer(new Media(new File(this.getClass().getResource(name).toString()).toString()));

		try {
			this.audioMedia = new MediaPlayer(new Media(this.getClass().getResource(name).toURI().toString()));
		} catch (URISyntaxException e) {
			System.out.println("error in resource loading");
			e.printStackTrace();
		}

	}
	
	public MusicControllerImpl(String name, GameModel game) {
		this(name);
		this.game = game;
		this.audioMedia.setOnEndOfMedia(() -> notifyObs());
	}

	@Override
	public void startMusic() {
		this.audioMedia.play();
	}

	@Override
	public void stopMusic() {
		this.audioMedia.stop();
	}
	
	@Override
	public Duration getDuration() {
		if( this.audioMedia == null) {
			throw new NullPointerException();
		}
		return this.audioMedia.getTotalDuration();
	}
	
	public MediaPlayer.Status getStatus(){
		return this.audioMedia.getStatus();
	}
	
	@Override
	public void pauseMusic() {
		this.audioMedia.pause();
	}

	@Override
	public void notifyObs() {
		this.game.onNotify();
	}
	
//	@Override
//	public void notifyEntity() {
//		if(this.audioMedia.getStatus() == Status.PAUSED) {
//			this.audioMedia.play();
//		} else if(this.audioMedia.getStatus() == Status.PLAYING) {
//			this.audioMedia.pause();
//		}
//	}
	

	

}
