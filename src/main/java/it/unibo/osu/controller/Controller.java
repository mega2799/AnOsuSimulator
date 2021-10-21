package it.unibo.osu.controller;


import java.io.IOException;

import it.unibo.osu.model.BeatMap;
import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GameStatus;
import it.unibo.osu.model.StatisticImpl;
import it.unibo.osu.model.User;
import it.unibo.osu.view.GameView;
import it.unibo.osu.view.PauseMenuView;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.util.Duration;
import it.unibo.osu.view.GameSceneController;

public class Controller {
	private final Stage view;
	private  GameSceneController sceneController;
	private final GameModel game;
	private final Robot robot;
	private final PauseMenuView pauseMenu;
	private final MusicControllerImpl musicController;
	
	/**
	 * Instantiates a new controller.
	 *
	 * @param name the {@link BeatMap} song title
	 * @param stage is the stage where the {@link Scene} is loaded when a song is selected
	 */
	public Controller(final String name,final Stage stage) {
		this.game = new GameModel("/beatmaps/" + name);

		try {

			StatisticImpl.getStat().readJson();
			StatisticImpl.getStat().addPlayer(User.getUsername()); // singleton (?)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.view = stage;

		this.initializeGameView();
		
		this.robot = new Robot();
		this.pauseMenu = new PauseMenuView();

		this.musicController = MusicControllerImplFactory.getMusicImpl("/tracks/" + this.game.getBeatMap().getSongName().stripLeading(), this.game);
		this.setInputHandler();
		this.changeScene();

	}
	
	private void initializeGameView() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/GameScene.fxml"));
		try {
			Pane fixedPane = ((Pane)this.view.getScene().getRoot());
			fixedPane.getChildren().add(0,loader.load());
			this.sceneController = ((GameSceneController) loader.getController());
			this.sceneController.init(game); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void changeScene() {
		Pane fixedPane = ((Pane)this.view.getScene().getRoot());
		FadeTransition fadeTrans = new FadeTransition(Duration.seconds(2),fixedPane);
		fadeTrans.setFromValue(1);
		fadeTrans.setToValue(0);
		fadeTrans.setOnFinished(finished -> {
			fixedPane.getChildren().retainAll(this.sceneController.getPane());
			fadeTrans.setFromValue(0);
			fadeTrans.setToValue(1);
			fadeTrans.setOnFinished(finishedChange -> {
				this.startGameLoop();
			});
			fadeTrans.playFromStart();
		});
		fadeTrans.play();
	}
	
	private void setInputHandler() {
		this.view.getScene().setOnKeyPressed(e ->{
			if(e.getCode().equals(KeyCode.SPACE) || this.game.getStatus() != GameStatus.PAUSE) {
				switch( e.getCode()) {
				case SPACE: 
					this.game.setPause();
					this.sceneController.setPausePane();
					if(this.game.getStatus().equals(GameStatus.PAUSE)) {
						this.musicController.pauseMusic();
					} else {
						this.musicController.startMusic();
					}
					
					break;
				case X: 
					this.robot.mouseClick(MouseButton.PRIMARY);
					break;
				case Z:
					this.robot.mouseClick(MouseButton.PRIMARY);
					break;
				default:
					break;
				}
			}
		});
	}
	
	private void startGameLoop()
	{
		new GameLoop(this.game, this.view, this.sceneController, this.musicController);
	}
		
}
