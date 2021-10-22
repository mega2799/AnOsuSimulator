package it.unibo.osu.appl;

import it.unibo.osu.controller.MusicControllerImpl;
import it.unibo.osu.controller.MusicControllerImplFactory;
import it.unibo.osu.model.BeatMapImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import it.unibo.osu.view.LoginMenuController;

public class Main extends Application{
	@Override
	public void start(Stage myStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/LoginMenu.fxml"));
		myStage.setScene(loader.load());
		((LoginMenuController)loader.getController()).init(myStage);
		myStage.initStyle(StageStyle.UTILITY);
		myStage.setFullScreen(true);
		myStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
