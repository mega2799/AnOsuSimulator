package it.unibo.osu.View;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EndGameView  extends Stage{
	public EndGameView() {
		Pane root = new Pane();

		this.setScene(new Scene(root,600,600));
		root.getChildren().add(new Label("EndGame"));
		this.show();
	}

}