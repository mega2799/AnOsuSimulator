package it.unibo.osu.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.osu.controller.Controller;
import it.unibo.osu.controller.MusicController;
import it.unibo.osu.controller.MusicControllerImpl;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuView extends Stage {
	private LoginMenu parent;
	
	private BorderPane root;
	
	private StackPane songList;
	
	private VBox vbox;
	
	private VBox options;
	
	private Scene scene;
	
	private ImageView avatar;
	
	private HBox infoBox; 
	
	private ImageView mainLogo;
	
	private String username;
	
	private Button playBtn;
	
	private Button optionBtn;
	
	private Button exitBtn;
	
	private Dimension full; 
	
	private MusicController ost;

	private static final int BOX_SPACING = 20;

	public MenuView(final String name, final LoginMenu parent) {
		this.parent = parent;
		this.username = name;
		this.root = new BorderPane(); // usare construcor super figo qua
		this.scene = new Scene(root);
	
		this.setScene(scene);
		//this.setFullScreen(true);
		this.full = getCurrentScreen();
		
		this.setResizable(true);

		imageOnBackground(getCurrentScreen()); // whole screen

		makeMenu(this.vbox); // left
		// lo vogliamo davvero mettere ?
		//makeLogo(this.mainLogo); // center 
		setAvatar(name); //top
		
		this.ost = new MusicControllerImpl("/music/menuOst.wav");
		this.ost.startMusic();
	}

	private void setAvatar(String name) {
		this.avatar = new ImageView(new Image(this.getClass().getResource("/image/avatarImage.png").toString()));
		avatar.setFitHeight(150);
		avatar.setFitWidth(150);

		Label username = new Label(name);
		username.setFont(new Font("Inconsolata Condensed ExtraBold", 30));
		username.setTextFill(Color.WHITE);
		username.setTranslateY(50);

		HBox hb = new HBox(BOX_SPACING);
		hb.getChildren().addAll(avatar, username);
		hb.setAlignment(Pos.TOP_RIGHT);

		this.root.setTop(hb);
	}

	private void imageOnBackground(Dimension screen) {
		Image image = new Image(this.getClass().getResource("/image/tokyo.png").toString());

		BackgroundSize bSize = new BackgroundSize(screen.getWidth(), screen.getHeight(), false, false, true, false);

	    this.root.setBackground(new Background(new BackgroundImage(image,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
	}
	
	private void makeLogo(ImageView logo) {
		logo = new ImageView(new Image(this.getClass().getResource("/image/uso_icon2.png").toString()));
		
		ScaleTransition transition = new ScaleTransition();
		transition.setNode(logo);
		transition.setAutoReverse(true);
		transition.setCycleCount(Animation.INDEFINITE);
		transition.setDuration(Duration.seconds(1));
		transition.setByX(0.1); 
		transition.setByY(0.1); 
		transition.play();
		
		logo.setFitHeight(340);
		logo.setFitWidth(340);
		
		this.root.setCenter(logo);
	}

	private void makeMenu(VBox menuBox) {
		menuBox = new VBox(250);
		
		this.playBtn = new MenuSkinButton("/buttonSkin/uso_icon_play.png").getSkinnedButton();
		this.optionBtn = new MenuSkinButton("/buttonSkin/uso_icon_options.png").getSkinnedButton();
		this.exitBtn = new MenuSkinButton("/buttonSkin/uso_icon_exit.png").getSkinnedButton();
		
		menuBox.setAlignment(Pos.CENTER_RIGHT);
		menuBox.setPrefWidth(250); // node position adjustment 
		menuBox.getChildren().addAll(this.playBtn, this.optionBtn, this.exitBtn);

		this.optionBtn.setOnMouseClicked(e -> {
			this.root.setRight(gameOptions(this.options)); // non mi convince apssargli come argumento
		});
		
		this.playBtn.setOnMouseClicked(e -> {
			this.ost.stopMusic();
			//this.root.setRight(scrollableSongList(this.songList));
			//new Controller("/beatmaps/legendsNeverDie.osu");
			//new Controller("/beatmaps/normal.osu");

			
			//new Controller(this.username, "/demonSlayer", "/Gurenge[Normal].osu");
			//harder
			//new Controller("/demonSlayer", "/Gurenge[Hard].osu");

			//new Controller("/sindromeRap", "/VirginitySyndrome[Normal].osu");
			//harder
			
			new Controller(this.username, "/sindromeRap", "/VirginitySyndrome[Insane].osu");
						
		});
		
		this.exitBtn.setOnMouseClicked(e -> {
			System.exit(1);
		});
		
		this.root.setLeft(menuBox); // lo lascio qua ?
	}

	private StackPane scrollableSongList(StackPane songList) {
		songList = new StackPane();

		ScrollPane scroll = new ScrollPane();

		FadeTransition fadeout = new FadeTransition(Duration.seconds(2), songList);
		fadeout.setFromValue(1.0);
		fadeout.setToValue(0);

		FadeTransition fadein = new FadeTransition(Duration.seconds(2), songList);
		fadein.setFromValue(0);
		fadein.setToValue(1);
		
		List<Button> l = List.of(new Button("matte"), new Button("manu"), new Button("osu"), new Button("matte"), new Button("manu"), new Button("osu")
				, new Button("matte"), new Button("manu"), new Button("osu"), new Button("matte"), new Button("manu"), new Button("osu"), new Button("matte"), new Button("manu"), new Button("osu"), new Button("matte"), new Button("manu"), new Button("Susw")
				, new Button("hello"), new Button("There"));
		
		VBox vb = new VBox(BOX_SPACING);

		vb.getChildren().addAll(l);


		scroll.setPrefHeight(400);
		scroll.setPrefWidth(100);
		scroll.setContent(vb);

		scroll.getStylesheets().add("/view/style.css");

		scroll.vvalueProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            //System.out.println("observable " + observable + " oldValue " + oldValue + " " + "newValue " + newValue);
            double hmin = scroll.getHmin();
            double hmax = scroll.getHmax();
            double hvalue = scroll.getHvalue();
            double contentWidth = scroll.getContent().getLayoutBounds().getWidth();
            double viewportWidth = scroll.getViewportBounds().getWidth();

            double hoffset = 
            Math.max(0, contentWidth - viewportWidth) * (hvalue - hmin) / (hmax - hmin);

            vb.setLayoutY(hoffset);
            //pane.requestLayout();                
        }
    });        

		songList.getChildren().add(scroll);
		songList.setPrefWidth(600);
		//songList.setStyle("-fx-background-color: transparent;");
		fadein.play();
		return songList;
	}

	private VBox gameOptions(VBox options) {
		options = new VBox(BOX_SPACING);
		
		FadeTransition fadeout = new FadeTransition(Duration.seconds(2), options);
		fadeout.setFromValue(1.0);
		fadeout.setToValue(0);

		FadeTransition fadein = new FadeTransition(Duration.seconds(2), options);
		fadein.setFromValue(0);
		fadein.setToValue(1);
		
		//row 1 
		Label resolution = new Label("Resolution");
		resolution.setFont(new Font("Inconsolata Condensed ExtraBold", 30));
		resolution.setTextFill(Color.WHITE);
		
		//row 2
		HBox hb = new HBox(BOX_SPACING);
		Button full = new NeonButton("Full screen").getButton();
		Button medium = new NeonButton("1920x1080").getButton();
		Button quadratic = new NeonButton("700x700").getButton();

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
		Slider slider = new Slider(0, 1, 100);	

		
		options.getChildren().addAll(volume, slider);
		
	
		//row 7 
		Label volumeSfx = new Label("Volume effetti");
		volumeSfx.setFont(new Font("Inconsolata Condensed ExtraBold", 30));
		volumeSfx.setTextFill(Color.WHITE);
		//row 8
		Slider sliderSfx = new Slider(0, 100, 1);	
		
		options.getChildren().addAll(volumeSfx, sliderSfx);
		
	
		full.setOnMouseClicked(e -> {
			//if(!getCurrentScreen().equals(this.full)) {
				//this.setFullScreen(true);
			System.out.print(this.full);
			this.parent.changeResolution(this.full.getWidth(), this.full.getHeight());
			//}
		});
	
		
		medium.setOnMouseClicked(e -> {
			//if(!getCurrentScreen().equals(new Dimension(1920, 1080))) {
				this.parent.changeResolution(1920, 1080); 
			//}
		});
		
		quadratic.setOnMouseClicked(e -> {
			//if(!getCurrentScreen().equals(new Dimension(700, 700))) {
				this.parent.changeResolution(700, 700);
			//}
		});
		
		Button undo = new Button("UNDO");
		
		
		undo.setOnMouseClicked(e ->{
			fadeout.play();
			
			fadeout.setOnFinished(ev -> {
				this.root.setRight(null);
			});
		});
		
		HBox hb2 = new HBox(undo);
		hb2.setAlignment(Pos.CENTER);
		
		options.getChildren().addAll(hb2);
		
		fadein.play();

		return options;
	}

	private Dimension getCurrentScreen() {
		//questo cast non convince  e poi forse non fuzniona
		//System.out.println(new Dimension((int)this.parent.getScene().getHeight(), (int)this.parent.getScene().getWidth()));
		//return new Dimension((int)this.parent.getScene().getHeight(), (int)this.parent.getScene().getWidth());
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public BorderPane getPane() {
		return this.root;
	}
	
	public String getUsername() {
		return this.username;
	}
}
