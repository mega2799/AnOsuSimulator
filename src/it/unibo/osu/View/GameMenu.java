package it.unibo.osu.View;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameMenu extends Parent{
	public GameMenu(){
		VBox menu0 = new VBox(15);
		VBox menu1 = new VBox(15);
		
		menu0.setTranslateX(565);
		menu0.setTranslateY(300);
	
		menu1.setTranslateX(100);
		menu1.setTranslateY(200);

		final int offset = 400;
		
	//	menu0.setTranslateX(offset);
		
		MenuButton newGameBtn = new MenuButton("Play Uso!");
		MenuButton exitBtn = new MenuButton("Exit");
		
		menu0.getChildren().addAll(newGameBtn, exitBtn);
		
		getChildren().addAll(menu0);
	}
}
