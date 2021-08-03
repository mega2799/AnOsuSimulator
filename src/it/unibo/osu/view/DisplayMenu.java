package it.unibo.osu.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.osu.controller.MusicControllerImpl;
import javafx.animation.FadeTransition;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DisplayMenu {
	
	private static final double SCENE_WIDTH = 1280;
	private static final double SCENE_HEIGHT = 720;
	private Pane gameRoot = new Pane();
	private Scene gameScene;
	private static MusicControllerImpl audio;
	
	private GameMenu gameOptions;

	public DisplayMenu(final Stage primary) {
		
		primary.setTitle("USo!");
		
		this.gameOptions = new GameMenu(primary, SCENE_WIDTH, SCENE_HEIGHT);

		try {
			setImage(gameOptions);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.gameScene = new Scene(this.gameRoot, SCENE_WIDTH, SCENE_HEIGHT);

		gameScene.setCursor(new ImageCursor(new Image("/wallpaper/biggerCursor.png")));
		// nel gioco sarebbe anche fattibile creare una shadow del cursore
		// sarebbe fattibile usando la funzione render, in cui prendo le posizioni del mouse
		// e utilizzo fadein e fadeout con un effetto per poter creare una traccia 
		
		primary.setScene(gameScene);
		primary.show();
	
}
	// questa funione qua non è molto carina, fa schifo va rivista completamente
	private void setImage(GameMenu g) throws IOException {
		InputStream is = Files.newInputStream(Paths.get("res/wallpaper/uso.png"));
		Image background = new Image(is);
		is.close(); // chiudo lo stream
		
		ImageView gg = new ImageView(new Image(Files.newInputStream(Paths.get("res/gif/intro.gif"))));
		gg.setTranslateX(200);
		gg.setTranslateY(30);

		
		// questo button serve solo a testare la gif e alune cose, da rimuovere alla fine
		MenuButton newGameBtn = new MenuButton("Buttton guy", 100, 50);
		
		newGameBtn.setOnMouseClicked(e -> {
			// ovviamente cosi non va bene, pero questo sono i codici da utilizzare per fare un animazione pop up
			FadeTransition fadeout = new FadeTransition(Duration.seconds(1), gg);
			fadeout.setFromValue(1.0);
			fadeout.setToValue(0);
			fadeout.play();
			
			FadeTransition fadein = new FadeTransition(Duration.seconds(1), gg);
			fadein.setFromValue(0);
			fadein.setToValue(1);
			fadein.play();
		});

		// le gif funzionano evviva 
		
		ImageView imgview = new ImageView(background);
		imgview.setFitWidth(SCENE_WIDTH);
		imgview.setFitHeight(SCENE_HEIGHT);
		gameRoot.getChildren().addAll(imgview, gg, g); // questa roba non si puo guardare assolutamente ......
	}
}