package it.unibo.osu.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GamePoints;
import it.unibo.osu.model.StatisticImpl;
import it.unibo.osu.model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EndGameController {

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView backToMenuButton;
    
    @FXML
    private GridPane scoreGrid;

    @FXML
    private Label username;
   
    @FXML
    private Label songName;

    @FXML
    private Label gameScore;

    @FXML
    private Label multi;

    @FXML
    private VBox historyBox;
    
    private ImageView rikkaGif;
    private GameModel game;
    
	private Map<GamePoints, Integer> map;
    private FXMLLoader loader; 

	public void init(GameModel game) {
		this.game = game;
//		this.map = game.getScoreManager().getStatistic();
//		this.username.setText(User.getUsername());
//		this.gameScore.setText(String.valueOf(game.getScoreManager().getPoints()));
//		this.multi.setText(String.valueOf(game.getScoreManager().getScore().getMaxMultiplier()) + "x");
//		writeOnGrid();
		loader = new FXMLLoader(this.getClass().getResource("/fxml/MainMenu.fxml"));
		try {
			((AnchorPane) this.pane.getScene().getRoot()).getChildren().add(0, loader.load());
			((MainMenuController)loader.getController()).init((Stage)this.pane.getScene().getWindow());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.initializeInputHandler();
		// non vuole farla vedere
// 		try {
//			this.rikkaGif = new ImageView(new Image(Files.newInputStream(Paths.get("src/main/resources/gif/rikka-takanashi-takanashi-rikka.gif"))));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		this.pane.getChildren().addAll(this.rikkaGif);

	}
	
	public void registerData() {
		this.map = game.getScoreManager().getStatistic();
		this.username.setText(User.getUsername());
		this.gameScore.setText(String.valueOf(game.getScoreManager().getPoints()));
		this.multi.setText(String.valueOf(game.getScoreManager().getScore().getMaxMultiplier()) + "x");
		writeOnGrid();
		try {
			StatisticImpl.getStat().addResult(this.username.getText(), this.gameScore.getText()); 
			StatisticImpl.getStat().writeJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//historyBox
		//makeBox(StatisticImpl.getStat().getPlayerHistory(this.username.getText()));
	}

	private void makeBox(List<String> playerHistory) {
		/*
		playerHistory.forEach(p -> {
			this.historyBox.getChildren().add(new Label(p));
		});
		*/
		this.historyBox.getChildren().addAll(new Label("ciao"));
		//this.historyBox.setVisible(true); //per ora 
	}

	/*
	 * Method used to write the statistic into the grid cells, modifing the text of the Labels took from the FXML
	 */
	private void writeOnGrid() {
		ObservableList<Node> childrens = this.scoreGrid.getChildren();
		int i = 0;
		final Iterator<GamePoints> it =  this.map.keySet().iterator();
		while(it.hasNext()) {
			GamePoints gp = it.next();
			modifyRowColumn(childrens, gp.toString(), i, 0);
			modifyRowColumn(childrens, this.map.get(gp).toString(), i++, 1);
		}
	}


	private void modifyRowColumn(ObservableList<Node> childrens, String string, int row, int column) {
		 for (Node node : childrens) {
			 /*
			  * i need to use Integer because GridPane.getRowIndex and GridPane.getColumnIndex can't return
			  * the 0 index, instead they return null, this solution avoid uso! to crash in an infinite loop
			 */
			 Integer rowIndex = GridPane.getRowIndex(node);
			 Integer columnIndex = GridPane.getColumnIndex(node);
			 
			 /*
			  * rowIndex.equals(null) make the window-creation-loop reappear
			  */
			 if(rowIndex == null) { 
				 rowIndex = 0;
			 }

			 if(columnIndex == null) {
				 columnIndex = 0;
			 }
			 
			 if(rowIndex.equals(row) && columnIndex.equals(column)) {
				 ((Label)node).setText(string);
		            break;
		        }
		    }
	}
	
	private void initializeInputHandler() {
		this.backToMenuButton.setOnMouseEntered(entered -> {
			this.backToMenuButton.setFitWidth(this.backToMenuButton.getFitWidth()+10);
			this.backToMenuButton.setFitHeight(this.backToMenuButton.getFitHeight()+10);
		});
		this.backToMenuButton.setOnMouseExited(exited -> {
			this.backToMenuButton.setFitWidth(this.backToMenuButton.getFitWidth()-10);
			this.backToMenuButton.setFitHeight(this.backToMenuButton.getFitHeight()-10);
		});
		this.backToMenuButton.setOnMouseClicked(event -> {
			((MainMenuController)loader.getController()).startAnimation();
			((AnchorPane)this.pane.getScene().getRoot()).getChildren().remove(this.pane);
		});
	}

}
