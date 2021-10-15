package it.unibo.osu.view;

import it.unibo.osu.controller.Controller;
import it.unibo.osu.model.BeatMap;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SongButtonController {

    @FXML
    private Text difficultyLabel;

    @FXML
    private Text nameLabel;
    @FXML
    private AnchorPane anchorPane;
    
    private String fileName;
    
    /**
     * @param fileName
     */
    public void init(String fileName) {
    	this.fileName = fileName;
    	BeatMap beatmap = new BeatMap("/beatmaps/" + fileName);
    	this.setSongName(beatmap.getBeatmapName());
    	this.setDifficulty(beatmap.getOverallDifficulty());
    	this.initializeInputHandler();
    }
    private void setSongName(String songName) {
    	this.nameLabel.setText(songName);
    }
    private void setDifficulty(double difficulty) {
    	this.difficultyLabel.setText(Double.toString(difficulty));
    }
    private void initializeInputHandler() {
    	this.anchorPane.setOnMouseClicked(clicked -> {
    		new Controller(this.fileName,(Stage) this.anchorPane.getScene().getWindow());
    	});
    }
    
}
