package it.unibo.osu.view;


import java.util.List;


import it.unibo.osu.controller.ScoreManager;
import it.unibo.osu.model.BeatMap;
import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.LifeBar;
import it.unibo.osu.model.Score;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class GameSceneController{

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
    
    private HitcircleViewFactory factory;
    
    public void init(GameModel game) {
    	this.game = game;
    	this.setBackground();
    	BeatMap beatmap = this.game.getBeatMap();
    	this.factory = new HitcircleViewFactory("/image/innerCircle.png", "/image/outerCircle.png", beatmap.getCircleSize(), beatmap.getOverallDifficulty(), beatmap.getApproachRate());
    }
    
     public void  render() {
    	this.lifebar.setProgress(this.game.getLifeBar().getHp()/LifeBar.MAXHP);
    	ScoreManager scoreManager = this.game.getScoreManager();
    	this.multiplier.setText("x" + Integer.toString(scoreManager.getMultiplier()));
    	this.points.setText(Integer.toString(scoreManager.getPoints()));
    	this.game.getCurrentHitbuttons().forEach(x -> {
    		HitcircleViewImpl hitcircleView = factory.getHitcircleView(x);
    		this.pane.getChildren().addAll(hitcircleView.getChildren());
    		hitcircleView.addObserver(this.game.getLifeBar());
    		hitcircleView.addObserver(this.game.getScoreManager());
    		hitcircleView.getParallelTransition().play();
    	});
    	this.game.getCurrentHitbuttons().clear();
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
