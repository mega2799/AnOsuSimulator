package it.unibo.osu.appl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import it.unibo.osu.view.LoginMenu;
import it.unibo.osu.view.MenuController;

public class Main extends Application{
	@Override
	public void start(Stage myStage) throws Exception {
		// DisplayMenu menu = new DisplayMenu(myStage);
		
		/*
		myStage.setTitle("Mi piacciono i gatti");
		
		FlowPane root = new FlowPane();
		
		Scene myScene = new Scene(root, 300 ,400);
		
		myStage.setScene(myScene);
		
		myStage.show();*/
		
		/*
		 * FXMLLoader loader = new
		 * FXMLLoader(this.getClass().getResource("/view/MenuView.fxml"));
		 * 
		 * try { Stage stage = loader.load(); stage.initStyle(StageStyle.UNDECORATED);
		 * stage.show(); // ((MenuController) loader.getController()).setInitialRes();
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */

		LoginMenu l = new LoginMenu();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
