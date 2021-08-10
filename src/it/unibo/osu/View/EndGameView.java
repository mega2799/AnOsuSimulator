package it.unibo.osu.View;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class EndGameView  extends Stage{
	private BorderPane root;
	private GridPane statPane;
	
	public EndGameView() {
		this.root = new BorderPane();
		this.statPane = new GridPane();
		
		gridSetter();

		this.setScene(new Scene(root,600,600));
		
		this.root.setTop(new Label("qua ci va il nome della canzone"));
		this.root.setCenter(statPane);
		this.root.setBottom(new Label("NOME GIOCATORE + PUNTI DELLA BETAMAP"));
		//root.getChildren().add(new Label("EndGame"));
		this.show();
	}

	private void gridSetter() {
		this.statPane.setGridLinesVisible(true);
		
		this.statPane.getColumnConstraints().add(new ColumnConstraints(200));
		this.statPane.getColumnConstraints().add(new ColumnConstraints(200));
		

		this.statPane.getRowConstraints().add(new RowConstraints(100));
		this.statPane.getRowConstraints().add(new RowConstraints(100));
		this.statPane.getRowConstraints().add(new RowConstraints(100));

		this.statPane.add(new Label("OK:"), 0, 0);
		this.statPane.add(new Label("100"), 1, 0);
	
		this.statPane.add(new Label("GREAT:"), 0, 1);
		this.statPane.add(new Label("200"), 1, 1);

	
		this.statPane.add(new Label("MEH:"), 0, 2);
		this.statPane.add(new Label("300"), 1, 2);
	}

}