package application;
	

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Scale;


public class Main extends Application {
	
	private int  pressed = 0;
	private ArrayList<Circle> list= new ArrayList<>();
	private ArrayList<ScaleTransition> transList = new ArrayList<>();
	private Random rnd = new Random();
	private int missed =  0;
	
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new BorderPane();
//		Circle crc = new Circle();
//		crc.setRadius(40.0);
//		crc.setCenterX(300);
//		crc.setCenterY(300);
//		crc.setFill(javafx.scene.paint.Color.AQUAMARINE);
		//crc.relocate(50, 50);
//		crc.setLayoutX(3);
//		crc.setLayoutY(3);
		
//		TranslateTransition transition = new TranslateTransition();
//		transition.setDuration(Duration.seconds(3));
//		transition.setToX(500);
//		transition.setToY(500);
//		transition.setNode(crc);
//		transition.setAutoReverse(true);
//		transition.setCycleCount(Animation.INDEFINITE);
//		transition.play();
		
		
//		ScaleTransition transitionIn = new ScaleTransition();
//		transitionIn.setDuration(Duration.seconds(3));
//		transitionIn.setByX(-0.5);
//		transitionIn.setByY(-0.5);
//		transitionIn.setNode(crc);
//		transitionIn.setAutoReverse(true);
//		transitionIn.setCycleCount(Animation.INDEFINITE);
//		transitionIn.play();		
//		root.getChildren().add(crc);
		
		Scene scene1 = new Scene(root,600,600);
		primaryStage.setTitle("Animations");
		primaryStage.setScene(scene1);
		
		
		
		for(int i=0;i<5;i++) {
			Circle circ = new Circle();
			circ.setRadius(40.0);
			circ.setCenterX(300);
			circ.setCenterY(300);
			circ.setFill(javafx.scene.paint.Color.AQUAMARINE);
			circ.setVisible(false);
			list.add(circ);
			root.getChildren().add(circ);
			
			circ.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
				System.out.println("pressed");
				this.pressedCircle();
				circ.setVisible(false);
			});	
		}
		list.forEach(c -> {
			ScaleTransition trans = new ScaleTransition(Duration.seconds(2), c);
//			trans.setByX(-0.5);
//			trans.setByY(-0.5);
//			trans.setToX(-0.5 );
//			trans.setToY(-0.5 );
			trans.setFromX(1);
			trans.setFromY(1);
			trans.setToX(0.5);
			trans.setToY(0.5);
//			trans.setCycleCount(1);
			trans.setAutoReverse(false);
			trans.setOnFinished(e ->{
				if(c.isVisible()) {
					trans.stop();
					missedCircle();
					System.out.println("missed");
					c.setVisible(false);
				}
			});
			transList.add(trans);
		});
		
		
		primaryStage.show();
		
		boolean status = true;
	
	
	    // game loop da capire in javafx, no sleep o cose varie..guardare tutorial.
	    

		new Thread(() -> {
			int indexCircle = -1;
			int next = 0;
			while(status == true) {
				//double start = System.currentTimeMillis();
				indexCircle =( indexCircle + 1 )%5;
				next = ( indexCircle + 1 )%5;
				update(list.get(next));
				render(indexCircle);		
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException();
				}
			}
		}).start();;



	}

	
	public static void main(String[] args) {
		launch(args);
	}
	void pressedCircle() {
		this.pressed +=1;
	}
	void missedCircle() {
		this.missed  += 1;
	}
	
	void update(Circle circ) {
//		Platform.runLater(() -> {
		list.forEach(c -> c.setRadius(40.0));
		circ.setCenterX(rnd.nextInt(500));
		circ.setCenterY(rnd.nextInt(500));
//		});
		
	}
	void render(int index) {
//		ScaleTransition transitionIn = new ScaleTransition();
//		transitionIn.setDuration(Duration.seconds(2));
//		transitionIn.setByX(-0.5);
//		transitionIn.setByY(-0.5);
//		transitionIn.setNode(circ);
//		transitionIn.setAutoReverse(true);
//		transitionIn.setCycleCount(1);
		list.get(index).setVisible(true);
//		transitionIn.setOnFinished(e ->{
//			circ.setVisible(false);
//			System.out.println("missed");
//		});
		ScaleTransition scl = transList.get(index);
		if(scl.getCurrentTime() == scl.getDuration() || scl.getStatus()==Status.STOPPED) {
			scl.playFromStart();
		} 
	}
}
