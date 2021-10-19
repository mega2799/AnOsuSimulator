package it.unibo.osu.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import it.unibo.osu.controller.MusicControllerImpl;
import it.unibo.osu.controller.MusicControllerImplFactory;
import it.unibo.osu.model.User;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenuController extends Resizeable{

    private static final int BOX_SPACING = 20;
    
    @FXML
    private StackPane optionPane;
    
	@FXML
    private StackPane optionButton;
	
	@FXML
	private StackPane playButton;

    @FXML
    private StackPane exitButton;

    @FXML
    private ImageView icon;

    @FXML
    private AnchorPane pane;
    
    private ScrollPane scrollPane;
    @FXML 
    private VBox vboxButtons;
  

	private ScaleTransition iconTrans;
	private Pane fixedPane;
	private FadeTransition fadeout;
	private Stage stage;

	private FadeTransition fadeoutOption;
	private FadeTransition fadeinOption;
	private HBox options;

	private TranslateTransition iconTranslateTransition;

	private FadeTransition menuOptionsFadeout;

	private ParallelTransition playEventParallelTransition;

	private FadeTransition songListFadeInTransition;

	private TranslateTransition songListTranslateTransition;
	
	private MusicControllerImpl scrollMenuSound;
	private MusicControllerImpl clickMenuSound;
	private List<StackPane> mainButtons;
//	private ScaleTransition scaleIconTransition;

	
    
    public void init(Stage stage) {
    	this.stage = stage;
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(this.getClass().getResource("/fxml/SongMenu.fxml"));
		try {
			fixedPane = ((Pane)this.pane.getParent());
//			fixedPane.getChildren().add(0,loader.load());
			this.scrollPane = (ScrollPane)loader.load();
			this.scrollPane.setLayoutX(-this.fixedPane.getPrefWidth());
			this.scrollPane.setOpacity(0);
			this.pane.getChildren().add(this.scrollPane);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.scrollMenuSound = MusicControllerImplFactory.getEffectImpl("/music/scrollMenuOptions.wav");
		this.clickMenuSound = MusicControllerImplFactory.getEffectImpl("/music/clickMenuOptions.wav");
    	this.setInputHandlers();
		this.initializeTransitions();
		this.initializeSounds();
		this.gameOptions();
		this.mainButtons = new ArrayList<>();
		this.mainButtons.add(this.playButton);
		this.mainButtons.add(this.optionButton);
		this.mainButtons.add(this.exitButton);
		
    }

	private void initializeSounds() {
		
	}

	private void setInputHandlers() {
		this.exitButton.setOnMouseClicked(exitEvent ->{
			this.clickMenuSound.onNotify();
			System.exit(0);
		});
		this.playButton.setOnMouseClicked(playEvent -> {
//			this.fadeout.play();
			if( this.options.getOpacity()==1) {
				this.fadeoutOption.play();
			}
			this.clickMenuSound.onNotify();
			this.playEventParallelTransition.play();
		});
		this.optionButton.setOnMouseClicked(optionsEvent -> {
			if( this.options.getOpacity()==1) {
				this.fadeoutOption.play();
			} else {
				this.fadeinOption.play();
			}
			this.clickMenuSound.onNotify();
		});
		this.exitButton.setOnMouseEntered(e1 -> {
			this.scrollMenuSound.onNotify();
			buttonEffect((Node)this.exitButton, MouseEvent.MOUSE_ENTERED);
			});
		this.exitButton.setOnMouseExited(e2 -> {
			buttonEffect((Node)this.exitButton, MouseEvent.MOUSE_EXITED);
		});
		this.optionButton.setOnMouseEntered(e3 -> {
			this.scrollMenuSound.onNotify();
			buttonEffect((Node)this.optionButton, MouseEvent.MOUSE_ENTERED);
			});
		this.optionButton.setOnMouseExited(e4 -> {
			buttonEffect((Node)this.optionButton, MouseEvent.MOUSE_EXITED);
		});
		this.playButton.setOnMouseEntered(e5 -> {
			this.scrollMenuSound.onNotify();
			buttonEffect((Node)this.playButton, MouseEvent.MOUSE_ENTERED);
			});
		this.playButton.setOnMouseExited(e6 -> {
			buttonEffect((Node)this.playButton, MouseEvent.MOUSE_EXITED);
		});
	}
	private void buttonEffect(Node node, EventType<MouseEvent> mouseEntered) {
		if( mouseEntered.equals(MouseEvent.MOUSE_ENTERED)) {
			node.setTranslateX(50);
		} else {
			node.setTranslateX(-50);
		}
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
		
		this.menuOptionsFadeout = new FadeTransition(Duration.seconds(1), this.vboxButtons);
		this.menuOptionsFadeout.setToValue(0);
		this.menuOptionsFadeout.setFromValue(1);
		this.menuOptionsFadeout.setOnFinished(finished -> {
			this.mainButtons.forEach(button -> button.setVisible(false));
		});
		
		this.iconTranslateTransition = new TranslateTransition(Duration.seconds(3), this.icon);
		this.iconTranslateTransition.setToX(1000);
		
		this.songListFadeInTransition = new FadeTransition(Duration.seconds(5), this.scrollPane);
		this.songListFadeInTransition.setToValue(1);
		
		this.songListTranslateTransition = new TranslateTransition(Duration.seconds(4), this.scrollPane);
		this.songListTranslateTransition.setToX(this.fixedPane.getPrefWidth());
//		this.scaleIconTransition = new ScaleTransition(Duration.seconds(3),this.icon);
//		
//		this.scaleIconTransition.setToX(0.5);
//		this.scaleIconTransition.setToY(0.5);
		
//		this.scaleIconTransition.setOnFinished(ev -> {
//			this.iconTrans.playFromStart();		
//		});
		
		this.playEventParallelTransition = new ParallelTransition(this.iconTranslateTransition, this.menuOptionsFadeout, this.songListFadeInTransition, 
				this.songListTranslateTransition);
//		this.playEventParallelTransition = new ParallelTransition(this.iconTranslateTransition, this.menuOptionsFadeout, this.songListFadeInTransition, 
//				this.songListTranslateTransition, this.scaleIconTransition);
		
		//fare comparire i bottoni uno dopo l altro?
//		Animation[] animations = this.scrollPane.getContent()
//		this.playEventParallelTransition.getChildren().addAll()
		
	}
	public void startAnimation() {
		this.iconTrans.play();
		//effetti sonori magari
	}
	private void gameOptions() {
		this.options = new HBox(BOX_SPACING);
		this.options.setOpacity(0.);
		
		this.fadeoutOption = new FadeTransition(Duration.seconds(2), options);
		fadeoutOption.setFromValue(1.0);
		fadeoutOption.setToValue(0);

		this.fadeinOption = new FadeTransition(Duration.seconds(2), options);
		this.fadeinOption.setFromValue(0);
		this.fadeinOption.setToValue(1);
		
		//row 1 
		Label resolution = new Label("Resolution");
		resolution .setFont(new Font("Inconsolata Condensed ExtraBold", 30));
		resolution .setTextFill(Color.WHITE);
		
		//row 2
		HBox hb = new HBox(BOX_SPACING);
		Button full = new NeonButton("Full screen").getButton();
		Button medium = new NeonButton("1920x1080").getButton();
		Button quadratic = new NeonButton("700x400").getButton();

		hb.getChildren().addAll(full, medium, quadratic);
		options.getChildren().addAll(resolution, hb);

		//row 3 
		Label showFPS = new Label("Show FPS on Game");
		showFPS.setFont(new Font("Inconsolata Condensed ExtraBold", 30));
		showFPS.setTextFill(Color.WHITE);
		

		//row 4 
		HBox hb1 = new HBox(BOX_SPACING);
		Button yes = new NeonButton("Yes").getButton();
		Button no = new NeonButton("No").getButton();
		
		hb1.getChildren().addAll(yes, no);
		options.getChildren().addAll(showFPS, hb1);

		//row 5 
		Label volume = new Label("Volume");
		volume.setFont(new Font("Inconsolata Condensed ExtraBold", 30));
		volume.setTextFill(Color.WHITE);
		//row 6
		Slider slider = new Slider(0, 1, 1);	
		slider.valueProperty().addListener(changed -> {
			User.setMusicVolume(slider.getValue());
		});
		options.getChildren().addAll(volume, slider);
		
	
		//row 7 
		Label volumeSfx = new Label("Volume effetti");
		volumeSfx.setFont(new Font("Inconsolata Condensed ExtraBold", 30));
		volumeSfx.setTextFill(Color.WHITE);
		//row 8
		Slider sliderSfx = new Slider(0, 1, 1);	
		sliderSfx.valueProperty().addListener(changed -> {
			User.setEffectVolume(sliderSfx.getValue());
		});
		options.getChildren().addAll(volumeSfx, sliderSfx);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		full.setOnMouseClicked(e -> {
			this.changeResolution(this.fixedPane, screenSize.width, screenSize.height);
			this.stage.setFullScreen(true);
		});
	
		
		medium.setOnMouseClicked(e -> {
			this.stage.setFullScreen(false);
			this.changeResolution(this.fixedPane, 1920, 1080);
		});
		
		quadratic.setOnMouseClicked(e -> {
			this.stage.setFullScreen(false);
			this.changeResolution(this.fixedPane, 640, 480);
		});
		
		this.optionPane.setPadding(new Insets(0, 0, 0, 30));
		this.optionPane.getChildren().add(options);
	}
	
	@Override
	public void changeResolution(Pane pane, double width, double height) {
		super.changeResolution(pane, width, height);
		this.stage.sizeToScene();
		this.stage.centerOnScreen();
	}
}
