package it.unibo.osu.Controller;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicControllerImpl implements MusicController {

	private MediaPlayer audioMedia;

	public MusicControllerImpl(final String name) {
		this.audioMedia = new MediaPlayer(new Media(new File(this.getClass().getResource(name).toString()).toString()));
	}
	
	@Override
	public void startMusic() {
		this.audioMedia.play();
	}

	@Override
	public void stopMusic() {
		this.audioMedia.stop();
	}

}
