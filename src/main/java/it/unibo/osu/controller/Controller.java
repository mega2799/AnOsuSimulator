package it.unibo.osu.controller;

import java.io.IOException;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GameStatus;
import it.unibo.osu.model.StatisticImpl;
import it.unibo.osu.view.GameView;
import it.unibo.osu.view.PauseMenuView;


import javafx.fxml.FXMLLoader;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import javafx.scene.robot.Robot;
import it.unibo.osu.view.GameSceneController;

public class Controller {
	private final GameView view;
	private  GameSceneController sceneController;
	private final GameModel game;
	private final Robot robot;
	private final PauseMenuView pauseMenu;

	// name sarebbe il package :::
	public Controller(final String username, final String name, final String beatmap) {
		this.game = new GameModel(name + beatmap);
		this.game.setUser(username);

		StatisticImpl.getStat().addPlayer(username); // singleton (?)
		StatisticImpl.getStat().setSong(name + beatmap);
		StatisticImpl.getStat().writeJson();

		this.view = new GameView();
		//here
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/GameScene.fxml"));
		
		try {
			this.view.setScene(loader.load());
			this.sceneController = ((GameSceneController) loader.getController());
			this.sceneController.init(game, name); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.view.show();
		
		
		
		this.robot = new Robot();
		this.pauseMenu = new PauseMenuView();
		//qui bisogna passare il nome della song giustamente 
		
		//this.musicController = new MusicControllerImpl(name + "/audio.wav", this.game);
		MusicControllerImpl.getAudio().setSong(name + "/audio.wav");
		
		
		this.setInputHandler();
		
		//new GameLoop(this.game, this.view, this.sceneController, this.musicController);

		new GameLoop(this.game, this.view, this.sceneController);
	}
	
	private void setInputHandler() {
		this.view.getScene().setOnKeyPressed(e ->{
			if(e.getCode().equals(KeyCode.SPACE) || this.game.getStatus() != GameStatus.PAUSE) {
				switch( e.getCode()) {
				case SPACE: 
					this.game.setPause();

					//this.musicController.pauseMusic();
					MusicControllerImpl.getAudio().pauseMusic();

					if(!this.pauseMenu.isShowing()) {
						this.pauseMenu.show();
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
		this.pauseMenu.getScene().setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.SPACE) && this.pauseMenu.isShowing()) {
				this.pauseMenu.close();
			}
			//this.musicController.startMusic();
			MusicControllerImpl.getAudio().startMusic();
			this.game.setPause();
		});
	}
	

		
}
