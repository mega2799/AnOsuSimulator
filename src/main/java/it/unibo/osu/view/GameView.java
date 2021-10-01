package it.unibo.osu.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//da sistemare, mettere stage in file fxml
public class GameView extends Stage{
	public GameView() {
		Pane root = new Pane();
		
		this.setScene(new Scene(root,600,600));
		root.getChildren().add(new Label("Game View"));
		this.show();

	}

}
