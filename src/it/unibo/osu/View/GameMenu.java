package it.unibo.osu.View;

import it.unibo.osu.Controller.MusicControllerImpl;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class GameMenu extends Parent{
	private final static double BUTTON_WEIGHT = 160;
	private final static double BUTTON_HEIGHT = 40;
	private static MusicControllerImpl audio;
	
	public GameMenu(final double sceneWidth, double sceneHeight){
		VBox menu0 = new VBox(15);
		VBox menu1 = new VBox(15);
		
		//menu0.setTranslateX(565);
		//menu0.setTranslateY(300);
	

		this.audio = new MusicControllerImpl("/tracks/joshiraku.wav");
		
		this.audio.startMusic();
		
		menu0.setTranslateX(computeWidht(sceneWidth, BUTTON_WEIGHT));
		menu0.setTranslateY(computeHeight(sceneHeight, BUTTON_HEIGHT));
	
		menu1.setTranslateX(100);
		menu1.setTranslateY(300);

		// questi in realta sarebbero dei pane, non proprio dei pulsanti
		MenuButton newGameBtn = new MenuButton("Play Uso!", BUTTON_WEIGHT, BUTTON_HEIGHT);
		// io dovrei far fermare la musica d'intro ma questo sarebbe da implementare con MVC 
		// quindi va sistemata, dato che la avvio nella classe DisplayMenu....
		MenuButton exitBtn = new MenuButton("Exit", BUTTON_WEIGHT, BUTTON_HEIGHT);
		
		NeonButton pressMeBtn = new NeonButton("Press me", sceneWidth, sceneHeight);

		newGameBtn.setOnMouseClicked(e -> {
			this.audio.stopMusic();
		});
		
		exitBtn.setOnMouseClicked(e -> {
			this.audio.stopMusic();
			System.exit(1);
		});
		
	//	menu0.getChildren().addAll(newGameBtn, exitBtn);
		menu0.getChildren().addAll(newGameBtn, exitBtn, pressMeBtn);
		
		getChildren().addAll(menu0);
	}

	private double computeWidht(final double width, final double buttonWidth) {
		return (width - buttonWidth) / 2;
	}

	private double computeHeight(final double height, final double buttonHeight) {
		return (height - buttonHeight )/ 2 + 100;
	}

}
