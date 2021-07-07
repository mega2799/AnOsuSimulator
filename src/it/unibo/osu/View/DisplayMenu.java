package it.unibo.osu.View;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DisplayMenu {
	
	private static final double SCENE_WIDTH = 1280;
	private static final double SCENE_HEIGHT = 720;
	private Pane gameRoot = new Pane();
	private Scene gameScene;
	
	private GameMenu gameOptions;
	public DisplayMenu(final Stage primary) {
		primary.setTitle("USo!");
		
		gameOptions = new GameMenu();

		try {
			setImage(gameOptions);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.gameScene = new Scene(this.gameRoot, SCENE_WIDTH, SCENE_HEIGHT);
		
		
		primary.setScene(gameScene);
		
		primary.show();
	
}


	private void setImage(GameMenu g) throws IOException {
		InputStream is = Files.newInputStream(Paths.get("res/wallpaper/uso.png"));
		Image background = new Image(is);
		is.close();
		ImageView imgview = new ImageView(background);
		imgview.setFitWidth(SCENE_WIDTH);
		imgview.setFitHeight(SCENE_HEIGHT);
		gameRoot.getChildren().addAll(imgview, g); // questa roba non si puo guardare assolutamente ......
	}
}