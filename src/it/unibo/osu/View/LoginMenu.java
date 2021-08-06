package it.unibo.osu.View;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LoginMenu extends Stage {
	private Pane pane;

	public LoginMenu() {
		this.pane = new StackPane();
		this.setScene(new Scene(pane));
		this.setFullScreen(true);

		ImageView logo = new ImageView(new Image(this.getClass().getResource("/image/uso_icon2.png").toString()));
		logo.setFitWidth(500);
		logo.setFitHeight(500);

		// cambiare el nome a questa qua
		ScaleTransition trans1 = new ScaleTransition();
		trans1.setNode(logo);
		trans1.setAutoReverse(true);
		trans1.setCycleCount(Animation.INDEFINITE);
		trans1.setDuration(Duration.seconds(1));
		trans1.setByX(0.1);
		trans1.setByY(0.1);
		trans1.play();
		this.pane.getChildren().add(logo);
		StackPane.setAlignment(logo, Pos.CENTER);
		this.show();
		logo.setOnMouseClicked(e -> {
			final TextField user = new TextField();
			user.setMaxWidth(300);
			user.setStyle("-fx-background-radius: 5em;");
			HBox hb = new HBox();
			hb.getChildren().addAll(new Label("Login"), user, new Label(" Desu!"));
			trans1.stop();
			this.pane.getChildren().add(hb);
			hb.setAlignment(Pos.CENTER);

			user.setOnAction(es -> {
				System.out.println(user.getText().toString()); // questa va inviato al menu e poi alle statistiche
				this.close();
				
				// funzionare funziona ma la transizione fa schifo 
				// vedere per un modo decente per passare da una finestra all altra
				FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MenuView.fxml"));
		  
		  try {
			  Stage stage = loader.load(); 
			  stage.initStyle(StageStyle.UNDECORATED);
			  stage.show(); // ((MenuController) loader.getController()).setInitialRes();
		  
		  } catch (IOException ex) { ex.printStackTrace(); }
			});
		}
		/*
		 * FXMLLoader loader = new
		 * FXMLLoader(this.getClass().getResource("/view/MenuView.fxml"));
		 * 
		 * try { Stage stage = loader.load(); stage.initStyle(StageStyle.UNDECORATED);
		 * stage.show(); // ((MenuController) loader.getController()).setInitialRes();
		 * 
		 * } catch (IOException ex) { ex.printStackTrace(); } }
		 */ );
	}

}
