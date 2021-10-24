package it.unibo.osu.view;

import it.unibo.osu.controller.MusicControllerImpl;
import it.unibo.osu.controller.MusicControllerImplFactory;
import it.unibo.osu.model.User;
import java.awt.Toolkit;
import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Class LoginMenuController.
 */
public class LoginMenuController extends Resizeable {

    public static final int FADEOUT_DURATION_SEC = 3;
    public static final double ICON_TRANSITION_RATE = 0.1;
    @FXML
    private Scene scene;

    @FXML
    private ImageView background;

    @FXML
    private ImageView icon;

    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane fixedPane;

    @FXML
    private TextField textField;

    private ScaleTransition iconTrans;

    private MusicControllerImpl welcomeMusic;

    private FadeTransition fadeout;

    private FXMLLoader loader;

    private Stage stage;

    private MusicControllerImpl clickSound;

    private MusicControllerImpl loginSound;

    private Timeline musicFadeout;

    /**
     * Init.
     *
     * @param stage the stage
     */
    public void init(final Stage stage) {
        this.stage = stage;
        loader = new FXMLLoader(
                this.getClass().getResource("/fxml/MainMenu.fxml"));
        try {
            this.fixedPane.getChildren().add(0, loader.load());
            ((MainMenuController) loader.getController()).init(stage);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        this.changeResolution(this.fixedPane,
                toolkit.getScreenSize().getWidth(),
                toolkit.getScreenSize().getHeight());
        this.setInputHandlers();
        this.initializeTransitions();
        this.initializeSounds();
        this.iconTrans.play();
        this.clickSound = MusicControllerImplFactory
                .getEffectImpl("/music/loginClickSound.wav");
        this.loginSound = MusicControllerImplFactory
                .getEffectImpl("/music/loginSound.wav");
        this.musicFadeout = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(this.welcomeMusic.getMediaPlayer()
                                .volumeProperty(), 1)),
                new KeyFrame(Duration.seconds(FADEOUT_DURATION_SEC), new KeyValue(
                        this.welcomeMusic.getMediaPlayer().volumeProperty(),
                        0)));
        this.welcomeMusic.startMusic();
//        this.stage.getScene().setCursor(new ImageCursor(new Image(this
//                .getClass().getResource("/wallpaper/cursor.png").toString())));
    }

    /**
     * Initialize transitions.
     */
    private void initializeTransitions() {
        this.iconTrans = new ScaleTransition();
        this.iconTrans.setNode(this.icon);
        this.iconTrans.setAutoReverse(true);
        this.iconTrans.setCycleCount(Animation.INDEFINITE);
        this.iconTrans.setDuration(Duration.seconds(1));
        this.iconTrans.setByX(ICON_TRANSITION_RATE);
        this.iconTrans.setByY(ICON_TRANSITION_RATE);

        this.fadeout = new FadeTransition();
        this.fadeout.setNode(this.fixedPane);
        this.fadeout.setFromValue(1);
        this.fadeout.setToValue(0);
        this.fadeout.setDuration(Duration.seconds(1));

        this.fadeout.setOnFinished(e -> {
            this.fixedPane.getChildren().remove(this.pane);
            this.fadeout.setFromValue(0);
            this.fadeout.setToValue(1);
            this.fadeout.setDuration(Duration.seconds(1));
            this.fadeout.setOnFinished(null);
            this.fadeout.playFromStart();
        });

    }

    /**
     * Initialize sounds.
     */
    private void initializeSounds() {
        this.welcomeMusic = MusicControllerImplFactory
                .getSimpleMusicImpl("/music/welcome_sound.wav");
    }

    /**
     * Sets the input handlers.
     */
    public void setInputHandlers() {
        this.icon.setOnMouseClicked(e -> {
            this.textField.setVisible(true);
            System.out.println("ok");
        });
        this.textField.setOnMouseClicked(clicked -> {
            this.clickSound.onNotify();
        });
        this.textField.setOnAction(ev -> {
            this.loginSound.onNotify();
            User.setUsername(this.textField.getText());
            this.musicFadeout.play();
            this.fadeout.play();
            ((MainMenuController) loader.getController()).startAnimation();
        });
    }

    /**
     * Change resolution.
     *
     * @param pane the pane
     * @param width the width
     * @param height the height
     */
    @Override
    public void changeResolution(final Pane pane, final double width, final double height) {
        super.changeResolution(pane, width, height);
        this.stage.sizeToScene();
    }

}
