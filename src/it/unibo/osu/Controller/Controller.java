package it.unibo.osu.Controller;



import it.unibo.osu.Model.GameModel;
import it.unibo.osu.Model.GameStatus;
import it.unibo.osu.View.GameView;
import it.unibo.osu.View.PauseMenuView;
import it.unibo.osu.util.HitobjectSelector;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.robot.Robot;

public class Controller {
	private final GameView view;
	private final GameModel game;
	private final Robot robot;
	private final PauseMenuView pauseMenu;
	private final MusicControllerImpl musicController;
	
	public Controller(final String name) {
		this.view = new GameView();
		this.game = new GameModel(name);
		this.robot = new Robot();
		this.pauseMenu = new PauseMenuView();
		this.musicController = new MusicControllerImpl(name,this.game);
		this.setInputHandler();
		
		new GameLoop(this.game, this.view, this.musicController);
	}
	
	
	private void setInputHandler() {
		this.view.getScene().setOnKeyPressed(e ->{
			if(e.getCode().equals(KeyCode.SPACE) || this.game.getStatus() != GameStatus.PAUSE) {
				switch( e.getCode()) {
				case SPACE: 
					this.game.setPause();
					
					if(this.musicController.getStatus().equals(Status.PAUSED)) {
						this.musicController.pauseMusic();
					} else {
						this.musicController.pauseMusic();
					}
					
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
		});
	}
	

		
}
