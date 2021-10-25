package it.unibo.osu.model;

/**
 * The Class User.
 */
public final class User {
    private static String username;

    private User() {
    }

    /** The Constant MAXVOLUME. */
    public static final double MAXVOLUME = 1;
    private static double musicVolume = MAXVOLUME;
    private static double effectVolume = MAXVOLUME;

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
    public static void setUsername(final String username) {
        User.username = username;
    }

    /**
     * Gets the music volume.
     *
     * @return the music volume
     */
    public static double getMusicVolume() {
        return musicVolume;
    }

    /**
     * Sets the music volume.
     *
     * @param musicVolume is set as the new music volume
     */
    public static void setMusicVolume(final double musicVolume) {
        User.musicVolume = musicVolume;
    }

    /**
     * Gets the effect volume.
     *
     * @return the effect volume
     */
    public static double getEffectVolume() {
        return effectVolume;
    }

    /**
     * Sets the effect volume.
     *
     * @param effectVolume is set as the new effect volume
     */
    public static void setEffectVolume(final double effectVolume) {
        User.effectVolume = effectVolume;
    }

}
