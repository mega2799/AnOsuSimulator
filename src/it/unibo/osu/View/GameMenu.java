package it.unibo.osu.View;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameMenu extends Parent{
	private final static double BUTTON_WEIGHT = 160;
	private final static double BUTTON_HEIGHT = 40;
	
	public GameMenu(final double sceneWidth, double sceneHeight){
		VBox menu0 = new VBox(15);
		VBox menu1 = new VBox(15);
		
		//menu0.setTranslateX(565);
		//menu0.setTranslateY(300);
	

		menu0.setTranslateX(computeWidht(sceneWidth, BUTTON_WEIGHT));
		menu0.setTranslateY(computeHeight(sceneHeight, BUTTON_HEIGHT));
	
		menu1.setTranslateX(100);
		menu1.setTranslateY(300);

		MenuButton newGameBtn = new MenuButton("Play Uso!", BUTTON_WEIGHT, BUTTON_HEIGHT);
		// io dovrei far fermare la musica d'intro ma questo sarebbe da implementare con MVC 
		// quindi va sistemata, dato che la avvio nella classe DisplayMenu....
		MenuButton exitBtn = new MenuButton("Exit", BUTTON_WEIGHT, BUTTON_HEIGHT);
		
		exitBtn.setOnMouseClicked(e -> {
			System.exit(1);
		});
		
		menu0.getChildren().addAll(newGameBtn, exitBtn);
		
		getChildren().addAll(menu0);
	}

	private double computeWidht(final double width, final double buttonWidth) {
		return (width - buttonWidth) / 2;
	}

	private double computeHeight(final double height, final double buttonHeight) {
		return (height - buttonHeight )/ 2;
	}

}
