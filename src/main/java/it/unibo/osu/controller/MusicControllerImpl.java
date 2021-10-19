package it.unibo.osu.controller;

//import java.io.File;
import java.net.URISyntaxException;

import it.unibo.osu.model.GameModel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
public abstract class MusicControllerImpl extends AbstractSubject implements MusicController, Observer {

	private MediaPlayer audioMedia;
//	private GameModel game;
	public MusicControllerImpl(final String name) {
		//this.audioMedia = new MediaPlayer(new Media(new File(this.getClass().getResource(name).toString()).toString()));

		try {
			this.audioMedia = new MediaPlayer(new Media(this.getClass().getResource(name).toURI().toString()));
		} catch (URISyntaxException e) {
			System.out.println("error in resource loading");
			e.printStackTrace();
		}

	}
	//mhh 
	public  MediaPlayer getMediaPlayer() {
		return this.audioMedia;
	}
	
//	public MusicControllerImpl(String name, GameModel game) {
//		this(name);
//		this.game = game;
//		this.audioMedia.setOnEndOfMedia(() -> notifyObs());
//	}

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
	public void onNotify() {
	}
	
	@Override
	public void notifyObs() {	
	}
	
	@Override
	public void setOnFinished(Runnable runnable) {		
	}
	
	
//	public GameModel getGameModel() {
//		return this.game;
//	}
//	@Override
//	public void notifyObs() {
//		this.game.onNotify();
//	}
	
//	@Override
//	public void notifyEntity() {
//		if(this.audioMedia.getStatus() == Status.PAUSED) {
//			this.audioMedia.play();
//		} else if(this.audioMedia.getStatus() == Status.PLAYING) {
//			this.audioMedia.pause();
//		}
//	}
	

	

}
