package it.unibo.osu.Controller;

//import java.io.File;
import java.net.URISyntaxException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class MusicControllerImpl implements MusicController{

	private MediaPlayer audioMedia;

	public MusicControllerImpl(final String name) {
		//this.audioMedia = new MediaPlayer(new Media(new File(this.getClass().getResource(name).toString()).toString()));

		try {
			this.audioMedia = new MediaPlayer(new Media(this.getClass().getResource(name).toURI().toString()));
		} catch (URISyntaxException e) {
			System.out.println("error in resource loading");
			e.printStackTrace();
		}

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
	
//	@Override
//	public void notifyEntity() {
//		if(this.audioMedia.getStatus() == Status.PAUSED) {
//			this.audioMedia.play();
//		} else if(this.audioMedia.getStatus() == Status.PLAYING) {
//			this.audioMedia.pause();
//		}
//	}
	

	

}
