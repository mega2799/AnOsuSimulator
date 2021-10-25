package it.unibo.osu.view;

import it.unibo.osu.controller.MusicControllerImpl;
import it.unibo.osu.controller.MusicControllerImplFactory;
import it.unibo.osu.model.GameModel;
import it.unibo.osu.model.GamePoints;
import it.unibo.osu.model.StatisticImpl;
import it.unibo.osu.model.User;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Class EndGameController.
 */
public class EndGameController {

    public static final int HOVER_INCREASE = 10;
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

    @FXML
    private MediaView videoBackground;

    private GameModel game;

    private Map<GamePoints, Integer> map;

    private FXMLLoader loader;

    private MusicControllerImpl buttonClickSound;

    private MusicControllerImpl buttonHoverSound;

    private MusicControllerImpl endgameEnteredSound;

    private AnchorPane mainMenuPane;

    /**
     * Inits the EndGame.
     *
     * @param game the game
     */
    public void init(final GameModel game) {
        this.game = game;
        this.buttonClickSound = MusicControllerImplFactory
                .getEffectImpl("/music/clickSongs.wav");
        this.buttonHoverSound = MusicControllerImplFactory
                .getEffectImpl("/music/scrollSongs.wav");
        this.endgameEnteredSound = MusicControllerImplFactory
                .getEffectImpl("/music/endGame.wav");
        this.setBackground();
        this.initializeInputHandler();
    }

    /**
     * Register data is a function called at the end and takes data like
     * username of the {@link User}, total score and writes them into the
     * {@link EndGameController} Labels, it also uses
     * {@link it.unibo.osu.model.Statistic} to make
     * statistics and write them into the .json file.
     */
    public void registerData() {
        this.map = game.getScoreManager().getStatistic();
        this.username.setText(User.getUsername());
        this.gameScore
                .setText(String.valueOf(game.getScoreManager().getPoints()));
        this.multi.setText(String.valueOf(
                game.getScoreManager().getScore().getMaxMultiplier()) + "x");
        this.songName.setText(this.game.getBeatMap().getBeatmapName());
        writeOnGrid();
        try {
            StatisticImpl.getStat().addResult(this.username.getText(),
                    this.gameScore.getText());
            StatisticImpl.getStat().writeJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * set the background, using an mp4 file, so we don't need to use a
     * {@link MusicControllerImpl}, that is under the Labels.
     */
    private void setBackground() {
        this.videoBackground.setMediaPlayer(new MediaPlayer(new Media(
                this.getClass().getResource("/video/Ayanami.mp4").toString())));
        this.videoBackground.getMediaPlayer().setCycleCount(Integer.MAX_VALUE);
    }

    /**
     * Write on grid is a Method used to write the statistic into the grid
     * cells, modifying the text of the Labels took from the FXML.
     */
    private void writeOnGrid() {
        final ObservableList<Node> childrens = this.scoreGrid.getChildren();
        int i = 0;
        final Iterator<GamePoints> it = this.map.keySet().iterator();
        while (it.hasNext()) {
            GamePoints gp = it.next();
            modifyRowColumn(childrens, gp.toString(), i, 0);
            modifyRowColumn(childrens, this.map.get(gp).toString(), i++, 1);
        }
    }

    /**
     * Modify row column.
     *
     * @param childrens the childrens
     * @param string    the string
     * @param row       the row
     * @param column    the column
     */
    private void modifyRowColumn(final ObservableList<Node> childrens,
         final String string, final int row, final int column) {
        for (Node node : childrens) {
            /*
             * i need to use Integer because GridPane.getRowIndex and
             * GridPane.getColumnIndex can't return the 0 index, instead they
             * return null, this solution avoid uso! to crash in an infinite
             * loop
             */
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            /*
             * rowIndex.equals(null) make the window-creation-loop reappear
             */
            if (rowIndex == null) {
                rowIndex = 0;
            }

            if (columnIndex == null) {
                columnIndex = 0;
            }

            if (rowIndex.equals(row) && columnIndex.equals(column)) {
                ((Label) node).setText(string);
                break;
            }
        }
    }

    /**
     * Initialize input handler.
     */
    private void initializeInputHandler() {
        this.backToMenuButton.setOnMouseEntered(entered -> {
            this.buttonHoverSound.onNotify();
            this.backToMenuButton
                    .setFitWidth(this.backToMenuButton.getFitWidth()
                    + HOVER_INCREASE);
            this.backToMenuButton
                    .setFitHeight(this.backToMenuButton.getFitHeight()
                    + HOVER_INCREASE);
        });
        this.backToMenuButton.setOnMouseExited(exited -> {
            this.backToMenuButton
                    .setFitWidth(this.backToMenuButton.getFitWidth()
                    - HOVER_INCREASE);
            this.backToMenuButton
                    .setFitHeight(this.backToMenuButton.getFitHeight()
                    - HOVER_INCREASE);
        });
        this.backToMenuButton.setOnMouseClicked(event -> {
            this.buttonClickSound.onNotify();
            ((MainMenuController) loader.getController()).startAnimation();
            this.videoBackground.getMediaPlayer().stop();
            this.changeScene();
        });
    }

    /**
     * Change scene.
     */
    private void changeScene() {
        final Pane fixedPane = ((Pane) this.pane.getScene().getRoot());
        final FadeTransition fadeTrans = new FadeTransition(Duration.seconds(2),
                fixedPane);
        fadeTrans.setFromValue(1);
        fadeTrans.setToValue(0);
        fadeTrans.setOnFinished(finished -> {
            fixedPane.getChildren().remove(this.pane);
            fadeTrans.setFromValue(0);
            fadeTrans.setToValue(1);
            fadeTrans.setOnFinished(null);
            fadeTrans.playFromStart();
        });
        fadeTrans.play();
    }

    /**
     * Enter end game.
     */
    public void enterEndGame() {
        this.endgameEnteredSound.onNotify();
        this.registerData();
        this.videoBackground.getMediaPlayer().play();
        loader = new FXMLLoader(
                this.getClass().getResource("/fxml/MainMenu.fxml"));
        try { this.mainMenuPane = loader.load();
            ((AnchorPane) this.pane.getScene().getRoot()).getChildren().add(0,
                    this.mainMenuPane);
            ((MainMenuController) loader.getController())
                    .init((Stage) this.pane.getScene().getWindow());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        final Pane fixedPane = ((Pane) this.pane.getScene().getRoot());
        final FadeTransition fadeTrans = new FadeTransition(Duration.seconds(1),
                fixedPane);
        fadeTrans.setFromValue(1);
        fadeTrans.setToValue(0);

        fadeTrans.setOnFinished(finished -> {
            this.pane.toFront();
            fixedPane.getChildren().retainAll(this.pane,this.mainMenuPane);
            fadeTrans.setFromValue(0);
            fadeTrans.setToValue(1);
            fadeTrans.setOnFinished(null);
            fadeTrans.playFromStart();

        });
        fadeTrans.play();
    }
}
