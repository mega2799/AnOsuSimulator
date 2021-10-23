package it.unibo.osu.appl;

import it.unibo.osu.view.LoginMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	@Override
	public void start(Stage myStage) throws Exception {
		final FXMLLoader loader = 
				new FXMLLoader(this.getClass().getResource("/fxml/LoginMenu.fxml"));
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
