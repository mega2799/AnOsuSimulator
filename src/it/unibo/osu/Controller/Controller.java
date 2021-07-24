package it.unibo.osu.Controller;

import Model.GameModel;
import Model.GameStatus;
import it.unibo.osu.View.GameView;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class Controller {
	private final GameView view;
	private final GameModel game;
	private final String songName;
	
	public Controller(final String name) {
		this.view = new GameView();
		this.songName = name;
		this.game = new GameModel(name);
		this.setInputHandler();

		//procedere con gameloop poi finire model 
		new GameLoop(this.game, this.view);
	}
	
	
	private void setInputHandler() {
		this.view.getScene().setOnKeyPressed(e ->{
			if(e.getCode().equals(KeyCode.SPACE) || this.game.getStatus() != GameStatus.PAUSE) {
				switch( e.getCode()) {
				case SPACE: 
					this.game.setPause();
					break;
				case X: 
					//lanciare x,z come se fosse un click
					//cercare come lanciare un evento stile mouseclick
					break;
				case Z:
					break;
				default:
					break;
				}
			}
		});

	}
}
