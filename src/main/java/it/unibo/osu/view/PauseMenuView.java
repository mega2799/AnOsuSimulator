package it.unibo.osu.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Class PauseMenuView.
 */
public class PauseMenuView extends Stage {
	
	/**
	 * Instantiates a new pause menu view.
	 */
	public PauseMenuView() {
		Pane root = new Pane();
		
		this.setScene(new Scene(root,600,600));
		root.getChildren().add(new Label("Pause Menu"));
	}

}
