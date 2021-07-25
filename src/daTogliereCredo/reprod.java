package daTogliereCredo;

import java.io.File;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;



public class reprod extends Application {
	MediaPlayer mediaplayer;
	
	public static void main(String[] args) {
		launch(args);
		}

	@Override
	public void start(Stage arg0) {
		
		Button btn_play, btn_pause, btn_stop;

		btn_play = new Button("Start");
		btn_pause = new Button("Pause");
		btn_stop = new Button("Stop");

		btn_play.setOnAction(e -> mediaplayer.play());
		
	/*		btn_play.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mediaplayer.play();
			}
		}); */ 

		btn_pause.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mediaplayer.pause();
			}
		});

		btn_stop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mediaplayer.stop();
				System.exit(1);
			}
		});
		
		String ma = "/home/mega/Music/QuintupletsOP.wav";
//		String ma = "/home/mega/Music/quint.wav";
		//String ma = "/home/mega/Music/rideit.wav";
		
		File f = new File(ma);
		
		Media hit = new Media(f.toURI().toString());
		
		mediaplayer = new MediaPlayer(hit);

		mediaplayer.setAutoPlay(true);
		
		//mediaplayer.setRate(1.5); // gestione tempo

		MediaView mediaView = new MediaView(mediaplayer);

		VBox root = new VBox();
		root.getChildren().addAll(btn_play,btn_pause,btn_stop,mediaView);


		Scene scene = new Scene(root, 500, 500);
		arg0.setScene(scene);

		arg0.show();

	}

}
