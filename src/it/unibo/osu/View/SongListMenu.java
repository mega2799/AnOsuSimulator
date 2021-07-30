package it.unibo.osu.View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SongListMenu {
	private Pane menuPane = new Pane();
	private Scene menuScene = new Scene(menuPane);
	private Stage secondary;
	private VBox box = new VBox(55);
	
	public SongListMenu() throws IOException{
		secondary = new Stage();

		box.setTranslateX(200);
		box.setTranslateY(150);
		
		ImageView background = new ImageView(new Image(Files.newInputStream(Paths.get("res/gif/blackRain.gif"))));
		
		Button nBtn = new NeonButton("first").getNeon();
		
		Button nBtnn = new NeonButton("second").getNeon();

		Button nBtnnn = new NeonButton("third").getNeon();

		Button nBtnnnn = new NeonButton("fourth").getNeon();
		
		// qua con un bello stream creiamo i pulsanti dall archivio delle canzoni
		
		box.getChildren().addAll(nBtn, nBtnn, nBtnnn, nBtnnnn);

		menuPane.getChildren().addAll(background, box);

		secondary.setScene(menuScene);

		secondary.show();
	}
}
