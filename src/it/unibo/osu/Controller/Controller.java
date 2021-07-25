package it.unibo.osu.Controller;



import it.unibo.osu.Model.GameModel;
import it.unibo.osu.Model.GameStatus;
import it.unibo.osu.View.GameView;
import it.unibo.osu.View.PauseMenuView;
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
import javafx.scene.robot.Robot;

public class Controller {
	private final GameView view;
	private final GameModel game;
	private final String songName;
	private final Robot robot;
	private final PauseMenuView pauseMenu;
	public Controller(final String name) {
		this.view = new GameView();
		this.songName = name;
		this.game = new GameModel(name);
		this.robot = new Robot();
		this.pauseMenu = new PauseMenuView();
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
