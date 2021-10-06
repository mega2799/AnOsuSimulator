package it.unibo.osu.controller;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.util.Clock;
import it.unibo.osu.view.EndgameView;
import it.unibo.osu.view.GameSceneController;
import it.unibo.osu.view.GameView;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
	private GameModel game;
	private GameView view;  // <-- forse meglio passare solo controller, con file fxml che ha dentro anche stage cos� da fare getStage all occorrenza
	private GameSceneController sceneController;
	private MusicController musicController;
	private long previous;

	public GameLoop(GameModel game, GameView view, GameSceneController sceneController, MusicController musicController) {
		this.game = game;
		this.view = view;
		this.sceneController = sceneController;
		this.musicController = musicController;
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
			this.musicController.startMusic();
			this.previous = now;
			break;
		case RUNNING:
			if( this.game.isGameOver()) {
				this.musicController.stopMusic();
				break;
			}
			this.tick(t,now); //conversione da nano a millisec
			break;
		case PAUSE:
			this.previous = now;
			break;
		case ENDGAME:
			// da implementare, aprir� una nuova scena o stage finale.
			this.view.close();
			///new EndgameView(this.game.getOsuClock().getTimeStatistic()); // passare map del clock in qualche modo 
			new EndgameView(this.game);
			this.stop();
			this.previous = now;
			break;
		default:
			System.out.println("non dovrebbe entrare qui.");
			throw new RuntimeException();		
		}
		
	}
	
	//togliere magic numbers qui e aggiungere render
	private void tick(long t,long now) {
		double updateTime = t * 1e-6; //fare una costante qui, togliere magic numbers
		this.game.update(updateTime); 
		this.sceneController.render();
		
		
		
		//long tmp = this.previous;
		if(t*1e-6 < 17 ) {
			try {
				long diff = (long) (17d - t*1e-6);
				Thread.sleep((long) (diff));
				this.game.update(diff); //aggiornare quei pochi millisecondi per sincronizzare ciclo <--
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
