package it.unibo.osu.controller;

public interface MusicFactory {

	
	MusicController effectSound(String effectName);
	
	MusicController song(String title);
}
