package it.unibo.osu.controller;

import java.io.IOException;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GameStatus;
import it.unibo.osu.model.StatisticImpl;
import it.unibo.osu.model.User;
import it.unibo.osu.view.GameView;
import it.unibo.osu.view.PauseMenuView;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;

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
//<<<<<<< HEAD
	private final MusicControllerImpl musicController;
	//private Pane fixedPane;
	
	public Controller(final String name, Stage stage) {
		this.game = new GameModel("/beatmaps/" + name);
//=======

//	// name sarebbe il package :::
//	public Controller(final String username, final String name, final String beatmap) {
//		this.game = new GameModel(name + beatmap);
//		this.game.setUser(username);

		StatisticImpl.getStat().addPlayer(User.getUsername()); // singleton (?)
//		StatisticImpl.getStat().setSong(name + beatmap);
		StatisticImpl.getStat().setSong(name);
		try {
			StatisticImpl.getStat().writeJson();
			StatisticImpl.getStat().readJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//>>>>>>> logicAndStatistic

		this.view = stage;

		this.initializeGameView();
		
		this.robot = new Robot();
		this.pauseMenu = new PauseMenuView();

		this.musicController = new MusicControllerImpl("/tracks/" + this.game.getBeatMap().getSongName().stripLeading(), this.game);
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
//					this.game.setPause();
//
//					this.musicController.pauseMusic();

//					if(!this.pauseMenu.isShowing()) {
//						this.pauseMenu.show();
//					} 
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
//		per testare funzionamento x,z:
//		this.view.getScene().setOnMouseClicked(e-> {
//			System.out.println(e.getSceneX() + " " + e.getSceneY());	
//		});
		
//		this.pauseMenu.getScene().setOnKeyPressed(e -> {
//			if(e.getCode().equals(KeyCode.SPACE) && this.pauseMenu.isShowing()) {
//				this.pauseMenu.close();
//			}
//			this.musicController.startMusic();
//			this.game.setPause();
//		});
//		this.sceneController.getPausePane().setOnKeyPressed(keyPressed -> {
//    		if(keyPressed.getCode().equals(KeyCode.SPACE) ) {
//    			this.sceneController.setPausePane();
//    		}
//			this.musicController.startMusic();
//			this.game.setPause();
//    	});
	}
	
	private void startGameLoop()
	{
		new GameLoop(this.game, this.view, this.sceneController, this.musicController);
	}
		
}
