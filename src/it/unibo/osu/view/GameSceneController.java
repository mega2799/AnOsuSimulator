package it.unibo.osu.view;


import java.awt.Toolkit;
import it.unibo.osu.controller.ScoreManager;
import it.unibo.osu.model.BeatMap;
import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.LifeBar;
import it.unibo.osu.util.Clock;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Scale;

public class GameSceneController{

	@FXML
	private Scene scene;
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
    
    //private HitcircleViewFactory factory;
    private HitCircleButtonFactory factory;
    
    //tentativo di clock
    private Clock clock;
    
    public void init(GameModel game) {
    	this.game = game;
    	this.setBackground();
    	BeatMap beatmap = this.game.getBeatMap();
    	//quella da sostituire per i hitbuttons
    	//this.factory = new HitcircleViewFactory("/image/innerCircle.png", "/image/outerCircle.png", beatmap.getCircleSize(), beatmap.getOverallDifficulty(), beatmap.getApproachRate());
    	this.factory = new HitCircleButtonFactory(beatmap.getCircleSize(), beatmap.getOverallDifficulty(), beatmap.getApproachRate());
    	
    	// matte 
    	this.clock = new Clock();
    	this.clock.start();
    	Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
    	this.changeResolution(toolkit.getScreenSize().getWidth(), toolkit.getScreenSize().getHeight());
    }
    
     public void  render() {
    	this.lifebar.setProgress(this.game.getLifeBar().getHp()/LifeBar.MAXHP);
    	ScoreManager scoreManager = this.game.getScoreManager();
    	this.multiplier.setText("x" + Integer.toString(scoreManager.getMultiplier()));
    	this.points.setText(Integer.toString(scoreManager.getPoints()));
    	this.game.getCurrentHitbuttons().forEach(x -> {
    		//HitcircleViewImpl hitcircleView = factory.getHitcircleView(x);
    		HitCircleButton hitcircleView = factory.getHitcircleView(x);
    		this.pane.getChildren().addAll(hitcircleView.getGroup());
    		hitcircleView.addObserver(this.game.getLifeBar());
    		hitcircleView.addObserver(this.game.getScoreManager());
    		hitcircleView.addObserver(this.clock);
    	//	hitcircleView.getParallelTransition().play();
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
    public void changeResolution(double width,double height) {
		// lo scale fa vedere l' immagine sotto lo stage, qualcosa non torna
		Scale scale = new Scale(width/this.pane.getWidth(),height/this.pane.getHeight());
		this.pane.getTransforms().add(scale);
		this.pane.setPrefHeight(height);
		this.pane.setPrefWidth(width);
	}
}
