package it.unibo.osu.model;

public class User {
	private static String username;
	
	/** The Constant MAXVOLUME. */
	public final static double MAXVOLUME = 1;
	private static double MusicVolume = MAXVOLUME;
	private static double EffectVolume = MAXVOLUME;
	
	/**
	 * Gets the user name.
	 *
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public static void setUsername(String username) {
		User.username = username;
	}

	/**
	 * Gets the music volume.
	 *
	 * @return the music volume
	 */
	public static double getMusicVolume() {
		return MusicVolume;
	}

	/**
	 * Sets the music volume.
	 *
	 * @param musicVolume the new music volume
	 */
	public static void setMusicVolume(double musicVolume) {
		MusicVolume = musicVolume;
	}

	/**
	 * Gets the effect volume.
	 *
	 * @return the effect volume
	 */
	public static double getEffectVolume() {
		return EffectVolume;
	}

	/**
	 * Sets the effect volume.
	 *
	 * @param effectVolume the new effect volume
	 */
	public static void setEffectVolume(double effectVolume) {
		EffectVolume = effectVolume;
	}
	
}
