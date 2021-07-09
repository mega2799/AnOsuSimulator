package it.unibo.osu.Player;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer implements Runnable{
	private MediaPlayer media;
	private String name;

	// VA ASSOLUTAMENTE SISTEMATO
	public MusicPlayer(final String name) {
		this.name = name;
		File f = new File(name);
		Media song = new Media(f.toURI().toString());
		this.media = new MediaPlayer(song);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.media.setAutoPlay(true);
	}
}
