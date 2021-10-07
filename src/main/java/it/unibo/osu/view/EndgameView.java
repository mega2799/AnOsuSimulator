package it.unibo.osu.view;

import java.util.Iterator;
import java.util.Map;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GamePoints;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;

public class EndgameView  extends Stage{
	private BorderPane root;
	private GridPane statPane;
	
	private GameModel game;

	public EndgameView(final GameModel game) {
		this.game = game;
		this.root = new BorderPane();
		this.statPane = new GridPane();
		
		gridSetter();

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setScene(new Scene(root, screen.getHeight(), screen.getWidth())); //da aggiustare
		Label topLbl = styledLabel("Nome song");
		this.root.setTop(topLbl);
		this.root.setAlignment(topLbl, Pos.TOP_CENTER);
		this.root.setCenter(statPane);
		Label btmLbl = styledLabel(this.game.getUser() + " has scored " + this.game.getScoreManager().getPoints());
		
		
		Button exitBtn = new Button("Close the game");
		exitBtn.setStyle("-fx-background-radius: 5em;");
		exitBtn.setOnMouseClicked(e -> {
			System.exit(1);
		});
		this.root.setBottom(btmLbl);
		this.root.setAlignment(btmLbl, Pos.BOTTOM_CENTER);
		this.root.setRight(exitBtn);
		this.root.setAlignment(exitBtn, Pos.CENTER_LEFT);
		this.setFullScreen(true);
		//root.getChildren().add(new Label("EndGame"));
		drawBackgroundImage(screen);
		this.show();
	}

	private void drawBackgroundImage(Dimension screen) {
		//Image image = new Image(this.getClass().getResource("/image/wallpaper.png").toString());

		Image image = new Image(this.getClass().getResource("/wallpaper/uso.png").toString());
		

		BackgroundSize bSize = new BackgroundSize(screen.getWidth(), screen.getHeight(), false, false, true, false);

	    this.root.setBackground(new Background(new BackgroundImage(image,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
		
		
		
		ImageView background = new ImageView(new Image(this.getClass().getResource("/image/rei.png").toString()));
		background.setFitHeight(this.getScene().getHeight());
		background.setFitWidth(this.getScene().getWidth());
		//this.pane.getChildren().add(background);
	}

	private void gridSetter() {
		this.statPane.setGridLinesVisible(true);
		
		this.statPane.getColumnConstraints().add(new ColumnConstraints(200));
		this.statPane.getColumnConstraints().add(new ColumnConstraints(200));
		

		this.statPane.getRowConstraints().add(new RowConstraints(100));
		this.statPane.getRowConstraints().add(new RowConstraints(100));
		this.statPane.getRowConstraints().add(new RowConstraints(100));
		this.statPane.getRowConstraints().add(new RowConstraints(100));
		
		final Map<GamePoints, Integer> map = this.game.getScoreManager().getStatistic();

		int i = 0;
		final Iterator<GamePoints> it =  map.keySet().iterator();
		while(it.hasNext()) {
			GamePoints gp = it.next();
			this.statPane.add(styledLabel(gp.toString()), 0, i);
			this.statPane.add(styledLabel(map.get(gp).toString()), 1, i++);
		}
	}

	private Label styledLabel(final String arg) {
		Label lbl = new Label(arg);
		lbl.setStyle("-fx-font-size: 31pt;"
				+ "    -fx-font-family: \"Segoe UI Semibold\";"
				+ "    -fx-text-fill: black;"
				+ "    -fx-opacity: 0.6;");
		return lbl;
	}
}