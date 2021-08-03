package it.unibo.osu.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SongListMenu {
	private Pane menuPane = new Pane();
	private Scene menuScene = new Scene(menuPane);
	private Stage secondary;
	
	public SongListMenu() throws IOException{
		secondary = new Stage();
		
		HBox butt = FXMLLoader.load(ClassLoader.getSystemResource("fxmlStuff/NeonButton.fxml"));
		
		butt.getChildren().get(0).setOnMouseClicked(e -> {
			System.out.println("Faccio qualcosa anche io allora");
		});
		menuPane.getChildren().add(butt);

		secondary.setScene(menuScene);
		
		secondary.show();
	}
}
