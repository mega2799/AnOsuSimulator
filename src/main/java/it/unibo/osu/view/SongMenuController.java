package it.unibo.osu.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SongMenuController implements Initializable {

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private Pane pane2;

    @FXML
    private ImageView imageView1;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox1;

    @FXML
    private Pane upperBorder;

    @FXML
    private Pane downBorder;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	this.scrollPane.setContent(vbox1);	
    	this.scrollPane.getStylesheets().add("/view/style.css");
    	try {
    		for(int i=0;i<10;i++) {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(this.getClass().getResource("/fxml/Song.fxml"));
    			this.vbox1.getChildren().add(loader.load());
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
