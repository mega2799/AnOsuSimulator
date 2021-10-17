package it.unibo.osu.model;

public class User {
	private static String username;
	public final static double MAXVOLUME = 1;
	private static double MusicVolume = MAXVOLUME;
	private static double EffectVolume = MAXVOLUME;
	
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		User.username = username;
	}

	public static double getMusicVolume() {
		return MusicVolume;
	}

	public static void setMusicVolume(double musicVolume) {
		MusicVolume = musicVolume;
	}

	public static double getEffectVolume() {
		return EffectVolume;
	}

	public static void setEffectVolume(double effectVolume) {
		EffectVolume = effectVolume;
	}
	
}
