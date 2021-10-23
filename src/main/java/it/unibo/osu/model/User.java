package it.unibo.osu.model;

/**
 * The Class User.
 */
public class User {
    private static String username;

    /** The Constant MAXVOLUME. */
    public final static double MAXVOLUME = 1;
    private static double MusicVolume = MAXVOLUME;
    private static double EffectVolume = MAXVOLUME;

    /**
     * Gets the username.
     *
     * @return the username
     */
    public static String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username is set as the new username
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
     * @param musicVolume is set as the new music volume
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
     * @param effectVolume is set as the new effect volume
     */
    public static void setEffectVolume(double effectVolume) {
        EffectVolume = effectVolume;
    }

}
