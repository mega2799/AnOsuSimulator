package it.unibo.osu.view;

import java.io.IOException;


import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MainMenuController extends Resizeable{

    @FXML
    private ImageView PauseButton;

    @FXML
    private ImageView exitButton;

    @FXML
    private ImageView icon;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView playButton;

	private ScaleTransition iconTrans;
	private Pane fixedPane;
	private FadeTransition fadeout;
	
    
    public void init() {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/SongMenu.fxml"));
		try {
			fixedPane = ((Pane)this.pane.getParent());
			fixedPane.getChildren().add(0,loader.load());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	this.setInputHandlers();
		this.initializeTransitions();
		this.initializeSounds();
		this.iconTrans.play();
    }

	private void initializeSounds() {
		
	}

	private void setInputHandlers() {
		this.exitButton.setOnMouseClicked(exitEvent ->{
			System.exit(0);
		});
		this.playButton.setOnMouseClicked(playEvent -> {
			this.fadeout.play();
		});
	}

	private void initializeTransitions() {
		this.iconTrans = new ScaleTransition();
		this.iconTrans.setNode(this.icon);
		this.iconTrans.setAutoReverse(true);
		this.iconTrans.setCycleCount(Animation.INDEFINITE);
		this.iconTrans.setDuration(Duration.seconds(1));
		this.iconTrans.setByX(0.1);
		this.iconTrans.setByY(0.1);
		
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
}
