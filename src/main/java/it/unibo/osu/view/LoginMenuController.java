package it.unibo.osu.view;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unibo.osu.controller.MusicControllerImpl;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class LoginMenuController extends Resizeable {

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
    

	
	public void init(){
		loader = new FXMLLoader(this.getClass().getResource("/fxml/MainMenu.fxml"));
		try {
			this.fixedPane.getChildren().add(0,loader.load());
			((MainMenuController) loader.getController()).init();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
    	this.changeResolution(this.fixedPane, toolkit.getScreenSize().getWidth(), toolkit.getScreenSize().getHeight());
		this.setInputHandlers();
		this.initializeTransitions();
		this.initializeSounds();
		this.iconTrans.play();
		this.welcomeMusic.startMusic();
	
//    	System.out.println(toolkit.getScreenSize().getHeight() + " " + toolkit.getScreenSize().getWidth());
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
	
	private void initializeSounds() {
		this.welcomeMusic = new MusicControllerImpl("/music/welcome_sound.wav");
	}

	public void setInputHandlers() {
		this.icon.setOnMouseClicked(e -> {
			this.textField.setVisible(true);
			System.out.println("ok");
		});
		this.textField.setOnAction(ev -> {
			this.fadeout.play();
		});
	}
	
}
