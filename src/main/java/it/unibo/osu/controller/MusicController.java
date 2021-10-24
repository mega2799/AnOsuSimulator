package it.unibo.osu.controller;

/**
 * The Interface MusicController.
 */
public interface MusicController {

    /**
     * Start the music.
     */
    void startMusic();

    /**
     * Stop the music.
     */
    void stopMusic();

    /**
     * Pause the music.
     */
    void pauseMusic();

    /**
     * Sets the on finished Action.
     *
     * @param runnable on finished song
     */
    void setOnFinished(Runnable runnable);

}