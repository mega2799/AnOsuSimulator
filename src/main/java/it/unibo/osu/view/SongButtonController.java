package it.unibo.osu.view;

import it.unibo.osu.controller.Controller;
import it.unibo.osu.controller.MusicControllerImpl;
import it.unibo.osu.controller.MusicControllerImplFactory;
import it.unibo.osu.model.BeatMap;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class SongButtonController {

    @FXML
    private Text difficultyLabel;

    @FXML
    private Text nameLabel;
    @FXML
    private AnchorPane anchorPane;
    
    private String fileName;
    private MusicControllerImpl scrollSound;
    private MusicControllerImpl clickSound;
    
    /**
     * @param fileName
     */
    public void init(String fileName) {
    	this.fileName = fileName;
    	BeatMap beatmap = new BeatMap("/beatmaps/" + fileName);
    	this.setSongName(beatmap.getBeatmapName());
    	this.setDifficulty(beatmap.getOverallDifficulty());
    	this.scrollSound = MusicControllerImplFactory.getEffectImpl("/music/scrollSongs.wav");
    	this.clickSound = MusicControllerImplFactory.getEffectImpl("/music/clickSongs.wav");
    	this.initializeInputHandler();
    }
    private void setSongName(String songName) {
    	this.nameLabel.setText(songName);
    }
    private void setDifficulty(double difficulty) {
    	this.difficultyLabel.setText(Double.toString(difficulty));
    }
    private void initializeInputHandler() {
    	Scale scale = new Scale(1.1, 1.1);
    	this.anchorPane.setOnMouseClicked(clicked -> {
    		this.clickSound.onNotify();
    		new Controller(this.fileName,(Stage) this.anchorPane.getScene().getWindow());
    	});
    	this.anchorPane.setOnMouseEntered(entered -> {
    		this.anchorPane.getTransforms().add(scale);
    		this.scrollSound.onNotify();
    	});
    	this.anchorPane.setOnMouseExited(exited -> {
    		this.anchorPane.getTransforms().remove(scale);
    	});
    	
    }
    
}
