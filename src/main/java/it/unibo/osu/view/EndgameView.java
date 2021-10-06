package it.unibo.osu.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GamePoints;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import javafx.scene.layout.Pane;
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
		this.setScene(new Scene(root,screen.getHeight(),screen.getWidth()));
		
		this.root.setTop(new Label("NOME SONG"));
		this.root.setCenter(statPane);
		//this.root.setBottom(makeChart(stat));
		this.root.setBottom(new Label(this.game.getUser() + "has scored" + this.game.getScoreManager().getPoints()));
		//root.getChildren().add(new Label("EndGame"));
		drawBackgroundImage();
		this.setFullScreen(true); //scazza tutto questa roba qua 
		this.show();
	}

	private Node makeChart(Map<String, GamePoints> stat) {
        final Axis yAxis = new NumberAxis();
        yAxis.setAutoRanging(true);
        //final NumberAxis xAxis = new NumberAxis();
        final Axis xAxis = new NumberAxis();
        xAxis.setAutoRanging(true);
        final AreaChart<Number,Number> ac = 
                new AreaChart<Number,Number>(xAxis,yAxis);
        XYChart.Series seriesApril= new XYChart.Series();
        
        System.out.println(stat.keySet());
        //stat.forEach((k,v) -> new XYChart.Data<>(dateFormat.format(k), v));
        //stat.forEach((k,v) -> seriesApril.getData().add( new XYChart.Data<>(dateFormat.format(k), v)));
        stat.forEach((k,v) -> seriesApril.getData().add(
        		new XYChart.Data<>(k, v)));

        ac.getData().add(seriesApril);

		return ac;
	}

	private String toDate(String key) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss:SSS");
        Date date = dateFormat.parse(key);
		return dateFormat.format(date);
	}


	private void drawBackgroundImage() {
		Image image = new Image(this.getClass().getResource("/image/wallpaper.png").toString());

		BackgroundSize bSize = new BackgroundSize(this.getScene().getWidth(), this.getScene().getHeight(), false, false, true, false);

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
		this.statPane.setGridLinesVisible(false);
		
		this.statPane.getColumnConstraints().add(new ColumnConstraints(200));
		this.statPane.getColumnConstraints().add(new ColumnConstraints(200));
		

		this.statPane.getRowConstraints().add(new RowConstraints(100));
		this.statPane.getRowConstraints().add(new RowConstraints(100));
		this.statPane.getRowConstraints().add(new RowConstraints(100));
		this.statPane.getRowConstraints().add(new RowConstraints(100));
		
		final Map<GamePoints, Integer> map = this.game.getScoreManager().getStatistic();

		//for(int i = 0; i < map.size() ;i++) {
		int i = 0;
		final Iterator<GamePoints> it =  map.keySet().iterator();
		while(it.hasNext()) {
			GamePoints gp = it.next();
			this.statPane.add(new Label(gp.toString()), 0, i);
			this.statPane.add(new Label(map.get(gp).toString()), 1, i);
			i++;
		}
		//this.statPane.setStyle("/view/gridPane.css");
		//this.statPane.getStyleClass().add("/view/gridPane.css");
	}

}