package it.unibo.osu.view;

import java.net.URL;
import java.util.ResourceBundle;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.LifeBar;
import it.unibo.osu.model.Score;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
    
    private GameModel game;
    
    public void init(GameModel game) {
    	this.game = game;
    	this.setBackground();
    }
    
	
    public void render() {
    	this.lifebar.setProgress(this.game.getLifeBar().getHp()/LifeBar.MAXHP);
    	Score score = this.game.getScore();
    	this.multiplier.setText("x" + Integer.toString(score.getMultiplier()));
    	this.points.setText(Integer.toString(score.getPoints()));
    	//aggiugnere cerchietti presi da model
    }
    private String getBackgroundType(String url) {
    	String ext = url.substring(url.indexOf(".") + 1);
        if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")) {
              return  "photo";
        } else {
        	return "video";
        }
    }
    private void setBackground() {
    	String fileName = game.getBeatMap().getBackground();
    	String type = getBackgroundType(fileName);
    	if( type.equals("photo")) {
    		this.backgroundImage.setImage(new Image(this.getClass().getResource("/wallpaper/" + fileName).toString()));
    	} else {
    		this.backgroundMedia.setMediaPlayer(new MediaPlayer(new Media(this.getClass().getResource("/video/" + fileName).toString())));
    		this.backgroundMedia.getMediaPlayer().play();
    	}
    }
  
}
