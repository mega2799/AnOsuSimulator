package it.unibo.osu.Appl;

import it.unibo.osu.View.DisplayMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage myStage) throws Exception {
		
		DisplayMenu menu = new DisplayMenu(myStage);
		/*
		myStage.setTitle("Mi piacciono i gatti");
		
		FlowPane root = new FlowPane();
		
		Scene myScene = new Scene(root, 300 ,400);
		
		myStage.setScene(myScene);
		
		myStage.show();*/
	}

	public static void main(String[] args) {
		launch(args);
	}

}
