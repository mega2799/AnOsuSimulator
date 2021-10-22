package it.unibo.osu.controller;

import java.io.IOException;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GameModelImpl;
import it.unibo.osu.view.EndGameController;
import it.unibo.osu.view.EndgameView;
import it.unibo.osu.view.GameSceneController;
import it.unibo.osu.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameLoop extends AnimationTimer {
	private GameModel game;
	private Stage view;  // <-- forse meglio passare solo controller, con file fxml che ha dentro anche stage cos� da fare getStage all occorrenza
	private GameSceneController sceneController;
	private MusicController musicController;
	private long previous;
	private FXMLLoader loader;
	/**
	 * Instantiates a new game loop, using a {@link GameModelImpl}  instance that models a match using all the necessary entity, a 
	 * {@link Stage} in which the game takes place, {@link MusicController} is how we reproduce the song into the game.
	 *
	 * @param game GameModel 
	 * @param view the stage 
	 * @param sceneController the scene controller where we can 
	 * @param musicController the music controller
	 */
	public GameLoop(GameModel game, Stage view, GameSceneController sceneController, MusicController musicController) {
		this.game = game;
		this.view = view;
		this.sceneController = sceneController;
		this.musicController = musicController;
		this.previous = System.nanoTime();
		this.loader = new FXMLLoader(this.getClass().getResource("/fxml/endGame.fxml"));
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
			this.sceneController.pauseHitbuttons();
			try {
				((AnchorPane) this.view.getScene().getRoot()).getChildren().add(0, loader.load());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//mi sa che stop effect sound non funzia perche` gia sono spawnati ormai i sounds.
			EndGameController controller = ((EndGameController) this.loader.getController());
			controller.init(this.game);
			controller.enterEndGame();
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
