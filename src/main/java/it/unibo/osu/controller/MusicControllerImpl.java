package it.unibo.osu.controller;

import it.unibo.osu.model.User;
import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The Class MusicControllerImpl.
 */
public abstract class MusicControllerImpl extends AbstractSubject
        implements MusicController, Observer {

    private MediaPlayer audioMedia;

    /**
     * Instantiates a new music controller implementation.
     *
     * @param name the song name
     */
    public MusicControllerImpl(final String name) {

        try {
            this.audioMedia = new MediaPlayer(new Media(
                    this.getClass().getResource(name).toURI().toString()));
        } catch (URISyntaxException e) {
            System.out.println("error in resource loading");
            e.printStackTrace();
        }

    }

    /**
     * Gets the {@link MediaPlayer}.
     *
     * @return the media player
     */
    public MediaPlayer getMediaPlayer() {
        return this.audioMedia;
    }

    @Override
    public void startMusic() {
        this.audioMedia.play();
    }

    @Override
    public void stopMusic() {
        this.audioMedia.stop();
    }

    /**
     * Gets the status of the {@link MediaPlayer}.
     *
     * @return the status
     */
    public MediaPlayer.Status getStatus() {
        return this.audioMedia.getStatus();
    }

    @Override
    public void pauseMusic() {
        this.audioMedia.pause();
    }

    @Override
    public void onNotify() {
    }

    @Override
    public void notifyObs() {
    }

    @Override
    public void setOnFinished(Runnable runnable) {
    }

    /**
     * Update volume.
     */
    public void updateVolume() {
        this.getMediaPlayer().setVolume(User.getMusicVolume());
    }

}
