package it.unibo.osu.view;

import java.util.ArrayList;
import java.util.List;

import it.unibo.osu.controller.MusicControllerImpl;
import it.unibo.osu.controller.MusicControllerImplFactory;
import it.unibo.osu.controller.MusicEffectController;
import it.unibo.osu.controller.ScoreManagerImpl;
import it.unibo.osu.model.BeatMap;
import it.unibo.osu.model.GameModelImpl;
import it.unibo.osu.model.LifeBar;
import javafx.animation.Animation.Status;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * The Class GameSceneController.
 */
public class GameSceneController {

    @FXML
    private AnchorPane pane;

    @FXML
    private MediaView backgroundMedia;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ProgressBar lifebar;

    @FXML
    private Label multiplier;

    @FXML
    private Label points;

    @FXML
    private Pane pausePane;

    private GameModelImpl game;

    private HitcircleViewFactory factory;

    private MusicEffectController effectController;

    private MusicControllerImpl pauseSound;

    private List<Transition> listTransitions;

    /**
     * Inits the {@link GameSceneController}.
     *
     * @param game the game
     */
    public void init(final GameModelImpl game) {
        this.game = game;
        this.setBackground();
        final BeatMap beatmap = this.game.getBeatMap();
        this.factory = new HitcircleViewFactory(beatmap.getCircleSize(),
                beatmap.getOverallDifficulty(), beatmap.getApproachRate());
        this.listTransitions = new ArrayList<>();
        this.effectController = new MusicEffectController("/music/hitSound.wav",
                "/music/missSound.wav");
        this.pauseSound = MusicControllerImplFactory
                .getEffectImpl("/music/pauseSound.wav");

    }

    /**
     * Render.
     */
    public void render() {
        this.clearTransitionList();
        this.lifebar
                .setProgress(this.game.getLifeBar().getHp() / LifeBar.MAXHP);
        final ScoreManagerImpl scoreManager = this.game.getScoreManager();
        this.multiplier
                .setText("x" + Integer.toString(scoreManager.getMultiplier()));
        this.points.setText(Integer.toString(scoreManager.getPoints()));
        this.game.getCurrentHitbuttons().forEach(x -> {
            HitcircleViewImpl hitcircleView = factory.getHitcircleView(x);
            this.pane.getChildren().addAll(hitcircleView.getChildren());
            hitcircleView.addObserver(this.effectController);
            hitcircleView.addObserver(this.game);
            hitcircleView.addObserver(this.game.getOsuClock());
            this.listTransitions.add(hitcircleView.getParallelTransition());
            this.listTransitions.add(hitcircleView.getScaleTransition());
            hitcircleView.getParallelTransition().play();
        });
        this.game.getCurrentHitbuttons().clear();
    }
    private enum BackgroundType{
        PHOTO,
        VIDEO
    }
    /**
     * Gets the background type.
     *
     * @param url the url
     * @return the background type
     */
    private BackgroundType getBackgroundType(final String url) {
        final String ext = url.substring(url.indexOf(".") + 1);
        if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png")
                || ext.equalsIgnoreCase("jpeg")) {
            return BackgroundType.PHOTO;
        } else {
            return BackgroundType.VIDEO;
        }
    }

    /**
     * Sets the background.
     */
    private void setBackground() {
        final String fileName = game.getBeatMap().getBackground();
        final BackgroundType type = getBackgroundType(fileName);
        if (type.equals(BackgroundType.PHOTO)) {
            this.backgroundImage.setImage(new Image(this.getClass()
                    .getResource("/wallpaper/" + fileName).toString()));
        } else {
            this.backgroundMedia.setMediaPlayer(new MediaPlayer(new Media(this
                    .getClass().getResource("/video/" + fileName).toString())));
            this.backgroundMedia.getMediaPlayer()
                    .setCycleCount(Integer.MAX_VALUE);
            this.backgroundMedia.getMediaPlayer().play();
        }
    }

    /**
     * Gets the pane.
     *
     * @return the pane
     */
    public AnchorPane getPane() {
        return this.pane;
    }

    /**
     * Sets the pause pane.
     */
    public void setPausePane() {
        this.pauseSound.onNotify();
        if (!this.isPausePaneVisible()) {
            this.pausePane.toFront();
            if (this.backgroundMedia.getMediaPlayer() != null) {
                this.backgroundMedia.getMediaPlayer().pause();
            }
        } else {
            this.pausePane.toBack();
            if (this.backgroundMedia.getMediaPlayer() != null) {
                this.backgroundMedia.getMediaPlayer().play();
            }
        }
        ;
        this.pauseHitbuttons();
    }

    /**
     * Pause hitbuttons.
     */
    public void pauseHitbuttons() {
        this.listTransitions.forEach(transition -> {
            if (transition.getStatus().equals(Status.PAUSED)) {
                transition.play();
            } else if (transition.getStatus().equals(Status.RUNNING)) {
                transition.pause();
            }
        });
    }

    /**
     * Clear transition list.
     */
    private void clearTransitionList() {
        if (this.listTransitions.size() > 50) {
            this.listTransitions.clear();
        }
    }

    /**
     * Gets the pause pane.
     *
     * @return the pause pane
     */
    public Pane getPausePane() {
        return this.pausePane;
    }

    /**
     * Checks if is pause pane visible.
     *
     * @return true, if is pause pane visible
     */
    public boolean isPausePaneVisible() {
        return this.pane.getChildren().indexOf(this.pausePane) != 0;
    }

}
