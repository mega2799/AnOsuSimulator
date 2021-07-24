package it.unibo.osu.Controller;




import Model.GameModel;
import Model.GameStatus;
import it.unibo.osu.View.GameView;
import it.unibo.osu.View.PauseMenu;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;


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
	
		//rendo ciclo deterministico, fissando loop a 60 fps, sfruttando il pulse di javafx
		//long tmp = this.previous;
		
		switch(this.game.getStatus()) {
		case START:
			this.game.initGameOnStart();
			this.previous = now;
			break;
		case RUNNING:
			this.tick(t,now);
			break;
		case PAUSE:
			// da sistemare, altrimenti apre un menu pausa ad ogni ciclo così...lol
			//new PauseMenu();
			this.previous = now;
			break;
		case ENDGAME:
			// da implem
			this.previous = now;
			break;
		default:
			System.out.println("non dovrebbe entrare qui.");
			throw new RuntimeException();		
		}
		
	}
	private void tick(long t,long now) {
		
		//this.game.update(t); 
		//this.view.render();
		
		
		
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
		//uncomment to see actual gameloop
		//System.out.println((1d/ (this.previous - tmp))*  1e9);
	}
	


}
