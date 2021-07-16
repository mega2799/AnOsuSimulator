package it.unibo.osu.View;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.osu.Controller.MusicControllerImpl;
import it.unibo.osu.Player.MusicPlayer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DisplayMenu {
	
	private static final double SCENE_WIDTH = 1280;
	private static final double SCENE_HEIGHT = 720;
	//private Pane gameRoot = new Pane();
	private StackPane gameRoot = new StackPane();
	private Scene gameScene;
	private static MusicControllerImpl audio;
	
	
	private ImageView im;
	
	
	private GameMenu gameOptions;

	public DisplayMenu(final Stage primary) {
		im = new ImageView();
		
		im.setImage(new Image(new File("/res/gif/intro.gif").toURI().toString()));
		//im.setImage(new Image(this.getClass().getResource("intro.gif").toExternalForm()));
		//questa cosa dovrebbe funzionare.... non sei riscito a mettere res come risorsa effettiva...
		
		primary.setTitle("USo!");
		
		gameOptions = new GameMenu(SCENE_WIDTH, SCENE_HEIGHT);

		try {
			setImage(gameOptions);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.gameScene = new Scene(this.gameRoot, SCENE_WIDTH, SCENE_HEIGHT);
		
		//mp = new MusicPlayer("joshiraku.wav");
		audio = new MusicControllerImpl("/tracks/joshiraku.wav");
		
		audio.startMusic();

	//	mp = new MusicPlayer("res/tracks/joshiraku.wav");
		
		//this.mp.run();
		
		primary.setScene(gameScene);
	
		primary.show();
	
}
	// questa funione qua non è molto carina
	private void setImage(GameMenu g) throws IOException {
		InputStream is = Files.newInputStream(Paths.get("res/wallpaper/uso.png"));
		Image background = new Image(is);
		is.close(); // chiudo lo stream
		ImageView imgview = new ImageView(background);
		imgview.setFitWidth(SCENE_WIDTH);
		imgview.setFitHeight(SCENE_HEIGHT);
		gameRoot.getChildren().addAll(imgview, g); // questa roba non si puo guardare assolutamente ......
	}
}