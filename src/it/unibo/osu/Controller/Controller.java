package it.unibo.osu.Controller;

import Model.GameModel;
import it.unibo.osu.View.GameView;
import javafx.animation.AnimationTimer;

public class Controller {
	private final GameView view;
	private final GameModel game;
	private final String songName;
	
	public Controller(final String name) {
		this.view = new GameView();
		this.songName = name;
		this.game = new GameModel(name);
		
		//procedere con gameloop poi finire model 
		new GameLoop(this.game, this.view);
	}
}
