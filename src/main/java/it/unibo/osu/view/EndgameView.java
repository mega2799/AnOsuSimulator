package it.unibo.osu.view;

import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GamePoints;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Map;
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

/**
 * The Class EndgameView.
 */
public class EndgameView extends Stage {

    private BorderPane root;

    private GridPane statPane;

    private GameModel game;

    /**
     * Instantiates a new endgame view.
     *
     * @param game the game
     */
    public EndgameView(final GameModel game) {

    }

    /**
     * Draw background image.
     *
     * @param screen the screen
     */
    private void drawBackgroundImage(Dimension screen) {
        Image image = new Image(
                this.getClass().getResource("/wallpaper/uso.png").toString());

        BackgroundSize bSize = new BackgroundSize(screen.getWidth(),
                screen.getHeight(), false, false, true, false);

        this.root.setBackground(new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bSize)));

        ImageView background = new ImageView(new Image(
                this.getClass().getResource("/image/rei.png").toString()));
        background.setFitHeight(this.getScene().getHeight());
        background.setFitWidth(this.getScene().getWidth());
    }

    /**
     * Grid setter.
     */
    private void gridSetter() {
        this.statPane.setGridLinesVisible(true);

        this.statPane.getColumnConstraints().add(new ColumnConstraints(200));
        this.statPane.getColumnConstraints().add(new ColumnConstraints(200));

        this.statPane.getRowConstraints().add(new RowConstraints(100));
        this.statPane.getRowConstraints().add(new RowConstraints(100));
        this.statPane.getRowConstraints().add(new RowConstraints(100));
        this.statPane.getRowConstraints().add(new RowConstraints(100));

        final Map<GamePoints, Integer> map = this.game.getScoreManager()
                .getStatistic();

        int i = 0;
        final Iterator<GamePoints> it = map.keySet().iterator();
        while (it.hasNext()) {
            GamePoints gp = it.next();
            this.statPane.add(styledLabel(gp.toString()), 0, i);
            this.statPane.add(styledLabel(map.get(gp).toString()), 1, i++);
        }
    }

    /**
     * Styled label.
     *
     * @param arg the arg
     * @return the label
     */
    private Label styledLabel(final String arg) {
        Label lbl = new Label(arg);
        lbl.setStyle("-fx-font-size: 31pt;"
                + "    -fx-font-family: \"Segoe UI Semibold\";"
                + "    -fx-text-fill: black;" + "    -fx-opacity: 0.6;");
        return lbl;
    }
}