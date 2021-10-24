package it.unibo.osu.controller;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GameModelImpl;
import it.unibo.osu.view.EndGameController;
import it.unibo.osu.view.GameSceneController;
import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * GameLoop class is where the game runs and where we can find the various
 * entities of it.
 */
public class GameLoop extends AnimationTimer {
    private GameModel game;
    private Stage view;
    private GameSceneController sceneController;
    private MusicController musicController;
    private long previous;
    private FXMLLoader loader;

    private final static double UPTIME = 1e-6;
    /*
     * This variable is to adjust the framerate to 60 fps.
     */
    private final static double MSFORFRAME = 17d; 

    /**
     * Instantiates a new game loop, using a {@link GameModelImpl} instance that
     * models a match using all the necessary entity, a {@link Stage} in which
     * the game takes place, {@link MusicController} is how we reproduce the
     * song into the game.
     *
     * @param game            GameModel
     * @param view            the stage
     * @param sceneController the scene controller where we can
     * @param musicController the music controller
     */

    public GameLoop(final GameModel game, final Stage view,
            final GameSceneController sceneController,
            final MusicController musicController) {
        this.game = game;
        this.view = view;
        this.sceneController = sceneController;
        this.musicController = musicController;
        this.previous = System.nanoTime();
        this.loader = new FXMLLoader(
                this.getClass().getResource("/fxml/endGame.fxml"));
        this.start();
    }

    @Override
    public void handle(long now) {
        long t = (now - this.previous);

        // rendo ciclo deterministico, fissando loop a 60 fps, sfruttando il
        // pulse
        // di javafx
        // long tmp = this.previous;

        switch (this.game.getStatus()) {
        case START:
            this.game.initGameOnStart();
            this.musicController.startMusic();
            this.previous = now;

            break;
        case RUNNING:
            if (this.game.isGameOver()) {
                this.musicController.stopMusic();
                break;
            }
            this.tick(t, now); // conversione da nano a millisec
            break;
        case PAUSE:
            this.previous = now;
            break;
        case ENDGAME:
            this.sceneController.pauseHitbuttons();
            try {
                ((AnchorPane) this.view.getScene().getRoot()).getChildren()
                        .add(0, loader.load());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            final EndGameController controller = ((EndGameController) this.loader
                    .getController());
            controller.init(this.game);
            controller.enterEndGame();
            this.stop();
            this.previous = now;
            break;
        default:
            System.out.println("It shoulnd't be here");
            throw new RuntimeException();
        }

    }

    private void tick(final long t, final long now) {
        final double updateTime = t * UPTIME;
        this.game.update(updateTime);
        this.sceneController.render();

        // long tmp = this.previous;
        if (updateTime < MSFORFRAME) {
            try {
                final long diff = (long) (MSFORFRAME - updateTime);
                Thread.sleep((long) (diff));
                /*
                 * Update those few seconds to synchronize loop
                 */
                this.game.update(diff);
                this.previous = (long) (now + diff * UPTIME);
                // this.previous = System.nanoTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.previous = now;
        }
        // uncomment to see actual gameloop
        // System.out.println((1d/ (this.previous - tmp))* 1e9);
    }

}
