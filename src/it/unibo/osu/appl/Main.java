package it.unibo.osu.appl;

import it.unibo.osu.model.BeatMap;
import it.unibo.osu.view.DisplayMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import it.unibo.osu.view.LoginMenu;

public class Main extends Application{
	@Override
	public void start(Stage myStage) throws Exception {
		new LoginMenu();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
