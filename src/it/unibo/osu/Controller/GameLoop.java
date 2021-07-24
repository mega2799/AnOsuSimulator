package it.unibo.osu.Controller;

import Model.GameModel;
import it.unibo.osu.View.GameView;
import javafx.animation.AnimationTimer;
import javafx.util.Duration;

public class GameLoop extends AnimationTimer {
	private GameModel game;
	private GameView view;
	private long previous;
	public GameLoop(GameModel game, GameView view) {
		this.game = game;
		this.view = view;
		this.previous = System.nanoTime();
		this.start();
	}
	@Override
	public void handle(long now) {
		long t =  (now - this.previous);
		//processInput();
		//this.game.update(t); 
		//this.view.render();
		
		//rendo ciclo deterministico, fissando loop a 60 fps 
		//long tmp = this.previous;
		if(t*1e-6 < 17 ) {
			try {
				long diff = (long) (17d - t*1e-6);
				Thread.sleep((long) (diff));
				this.previous = (long) (now + diff*1e6);
				//this.previous = System.nanoTime();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			this.previous = now;
		}
		
		//uncomment this and "tmp" variable to see actual fps rate
		//System.out.println((1d/ (this.previous - tmp))*  1e9);

	}

}
