package it.unibo.osu.view;

import it.unibo.osu.controller.Controller;
import it.unibo.osu.controller.MusicControllerImpl;
import it.unibo.osu.controller.MusicControllerImplFactory;
import it.unibo.osu.model.BeatMap;
import it.unibo.osu.model.BeatMapImpl;
import it.unibo.osu.model.User;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SongButtonController {

    public static final int TRAILERMUSIC_START_SECONDS = 20;
    public static final double SCALE_HOVER = 1.1;
    @FXML
    private Text difficultyLabel;

    @FXML
    private Text nameLabel;

    @FXML
    private AnchorPane anchorPane;

    private String fileName;

    private MusicControllerImpl scrollSound;

    private MusicControllerImpl clickSound;

    private MusicControllerImpl song;

    /**
     * @param fileName
     */
    public void init(final String fileName) {
        this.fileName = fileName;
        BeatMap beatmap = new BeatMapImpl("/beatmaps/" + fileName);
        this.setSongName(beatmap.getBeatmapName());
        this.setDifficulty(beatmap.getOverallDifficulty());
        this.scrollSound = MusicControllerImplFactory.getEffectImpl("/music/scrollSongs.wav");
        this.clickSound = MusicControllerImplFactory.getEffectImpl("/music/clickSongs.wav");
//      System.out.println("/tracks/" + beatmap.getSongName().stripLeading());
        this.song = MusicControllerImplFactory.getSimpleMusicImpl("/tracks/" + beatmap.getSongName().stripLeading());
        this.song.getMediaPlayer().setStartTime(Duration.seconds(TRAILERMUSIC_START_SECONDS));
        this.initializeInputHandler();
    }
    private void setSongName(final String songName) {
        this.nameLabel.setText(songName);
    }
    private void setDifficulty(final double difficulty) {
        this.difficultyLabel.setText("Difficulty: " + Double.toString(difficulty));
    }
    private void initializeInputHandler() {
        Scale scale = new Scale(SCALE_HOVER, SCALE_HOVER);
        this.anchorPane.setOnMouseClicked(clicked -> {
            if (clicked.getClickCount() == 2) {
                this.song.stopMusic();
                this.clickSound.onNotify();
                new Controller(this.fileName, (Stage) this.anchorPane.getScene().getWindow());
            } else {
                this.song.stopMusic();
                this.song.startMusic();
            }
//          new Controller(this.fileName,(Stage) this.anchorPane.getScene().getWindow());   
        });
        this.anchorPane.setOnMouseEntered(entered -> {
            this.anchorPane.getTransforms().add(scale);
            this.scrollSound.onNotify();
        });
        this.anchorPane.setOnMouseExited(exited -> {
            this.anchorPane.getTransforms().remove(scale);
            this.song.stopMusic();
        });
    }
    public void updateVolume() {
        this.song.getMediaPlayer().setVolume(User.getMusicVolume());
        this.clickSound.getMediaPlayer().setVolume(User.getEffectVolume());
        this.scrollSound.getMediaPlayer().setVolume(User.getEffectVolume());
    }
}
