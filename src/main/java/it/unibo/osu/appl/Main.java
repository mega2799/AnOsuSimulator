package it.unibo.osu.appl;

import it.unibo.osu.model.BeatMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import it.unibo.osu.view.LoginMenu2;
import it.unibo.osu.view.LoginMenuController;

public class Main extends Application{
	@Override
	public void start(Stage myStage) throws Exception {
		//new LoginMenu2();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/LoginMenu.fxml"));
		myStage.setScene(loader.load());
		((LoginMenuController)loader.getController()).init(myStage);
		myStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
