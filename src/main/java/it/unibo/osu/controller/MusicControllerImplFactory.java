package it.unibo.osu.controller;

import it.unibo.osu.model.GameModelImpl;
import it.unibo.osu.model.User;

/**
 * A factory for creating MusicControllerImpl objects.
 */
public class MusicControllerImplFactory {

    /**
     * Gets the simple music.
     *
     * @param name is the title
     * @return the simple music
     */
    public static MusicControllerImpl getSimpleMusicImpl(final String name) {
        return new Music(name);
    }

    private static class Music extends MusicControllerImpl{

        public Music(final String name) {
            super(name);
            super.getMediaPlayer().setVolume(User.getMusicVolume());
        }

    }

    /**
     * Gets the music base implementation.
     *
     * @param name is the title
     * @param game is gameModel object
     * @return the base music implementation
     */
    public static MusicControllerImpl getMusicImpl(final String name,
            final GameModelImpl game) {

        return new IngameMusic(name, game);

    }

    private static class IngameMusic extends MusicControllerImpl {

        private GameModelImpl game;

        public IngameMusic (final String name, final GameModelImpl game) {
            super(name);
            super.getMediaPlayer().setOnEndOfMedia(() -> this.notifyObs());
            super.getMediaPlayer().setVolume(User.getMusicVolume());
            this.game = game;
        }

        @Override
        public void notifyObs() {
            this.game.onNotify();
        }

    }

    /**
     * Gets the effect impl.
     *
     * @param name the name
     * @return the effect impl
     */
    public static MusicControllerImpl getEffectImpl(String name) {
        return new Effect(name);
    }

    private static class Effect extends MusicControllerImpl{
        public Effect(String name) {
            super(name);
            super.getMediaPlayer().setVolume(User.getEffectVolume());
        }

        @Override
        public void onNotify() {
            super.stopMusic();
            super.startMusic();
        }

        @Override
        public void updateVolume() {
            this.getMediaPlayer().setVolume(User.getEffectVolume());
        }

    }

}
