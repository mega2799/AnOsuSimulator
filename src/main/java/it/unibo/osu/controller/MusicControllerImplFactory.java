package it.unibo.osu.controller;


import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.MusicType;
import it.unibo.osu.model.User;

public class MusicControllerImplFactory {
	
	public static MusicControllerImpl getSimpleMusicImpl(String name) {
		return new MusicControllerImpl(name) {
			
			{super.getMediaPlayer().setVolume(User.getMusicVolume());}
			
		};
	}
	
	public static MusicControllerImpl getMusicImpl(String name,GameModel game) {
		return new MusicControllerImpl(name) {
			
			{super.getMediaPlayer().setOnEndOfMedia(() -> this.notifyObs());
			 super.getMediaPlayer().setVolume(User.getMusicVolume());}
			
			@Override
			public void notifyObs() {
				game.onNotify();
			}
		};
	}
	public static  MusicControllerImpl getEffectImpl(String name) {
		return new MusicControllerImpl(name) {
			
			{super.getMediaPlayer().setVolume(User.getEffectVolume());}
			
			@Override
			public void onNotify() {
				super.stopMusic();
				super.startMusic();
			}
			
			@Override
			public void updateVolume() {
				this.getMediaPlayer().setVolume(User.getEffectVolume());
			}
		};
	}
}
