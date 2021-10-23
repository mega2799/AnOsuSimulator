package it.unibo.osu.controller;

/**
 * A factory for creating Music objects.
 */
public interface MusicFactory {

    /**
     * Effect sound.
     *
     * @param effectName the effect name
     * @return the music controller
     */
    MusicController effectSound(String effectName);

    /**
     * Song.
     *
     * @param title the title
     * @return the music controller
     */
    MusicController song(String title);
}
