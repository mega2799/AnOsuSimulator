package it.unibo.osu.View;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PauseMenu extends Stage {
	public PauseMenu() {
		Pane root = new Pane();
		
		this.setScene(new Scene(root,600,600));
		root.getChildren().add(new Label("Pause Menu"));
		this.show();
	}
}
