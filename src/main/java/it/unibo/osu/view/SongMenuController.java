package it.unibo.osu.view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.unibo.osu.controller.Controller;
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
    
    private List<AnchorPane> songButtons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	this.songButtons = new ArrayList<>();
    	this.scrollPane.setContent(vbox1);	
    	this.scrollPane.getStylesheets().add("/view/style.css");
    	this.initializeButtons();
    }
    
    public List<AnchorPane> getSongButtons(){
    	return this.songButtons;
    }
    
    private void initializeButtons() {
    	File[] files = new File(this.getClass().getResource("/beatmaps").getPath()).listFiles();
    	for(File file: files) {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(this.getClass().getResource("/fxml/Song.fxml"));
    			AnchorPane song = loader.load();
    			((SongButtonController) loader.getController()).init(file.getName());
    			songButtons.add(song);
    			this.vbox1.getChildren().add(song);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
   
}
