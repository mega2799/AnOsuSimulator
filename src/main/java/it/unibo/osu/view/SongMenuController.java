package it.unibo.osu.view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//import com.sun.prism.ResourceFactoryListener;

import it.unibo.osu.appl.Launcher;
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

    private List<SongButtonController> controllerList;
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

    	final String path = "beatmaps/";
    	final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
    	this.controllerList = new ArrayList<>();
    	try {
    		if(jarFile.isFile()) {  // Run with JAR file
    			final JarFile jar = new JarFile(jarFile);
    			final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
    			while(entries.hasMoreElements()) {
    				final String name = entries.nextElement().getName();
    				if (name.contains(path) && !name.equals(path)) { //filter according to the path
    					FXMLLoader loader = new FXMLLoader();
    					loader.setLocation(this.getClass().getResource("/fxml/Song.fxml"));
    					AnchorPane song = loader.load();
//    					((SongButtonController) loader.getController()).init(name.substring(path.length()));
    					SongButtonController controller = ((SongButtonController) loader.getController());
    					this.controllerList.add(controller);
    					controller.init(name.substring(path.length()));
    					this.songButtons.add(song);
    					this.vbox1.getChildren().add(song);
    				}
    			}
    			jar.close();
    		} else { // Run with IDE
    			final URL url = Launcher.class.getResource("/"+ path.subSequence(0, path.length()-1));
    			if (url != null) {
    				try {
    					final File apps = new File(url.toURI());
    					for (File app : apps.listFiles()) {
    						FXMLLoader loader = new FXMLLoader();
    						loader.setLocation(this.getClass().getResource("/fxml/Song.fxml"));
    						AnchorPane song = loader.load();
//    						((SongButtonController) loader.getController()).init(app.getName());
    						SongButtonController controller = ((SongButtonController) loader.getController());
        					this.controllerList.add(controller);
        					controller.init(app.getName());
    						this.songButtons.add(song);
    						this.vbox1.getChildren().add(song);    	            }
    				} catch (URISyntaxException ex) {
    				}
    			}
    		}
    	}catch(IOException io ) {

    	}
    }
    public void updateEffectsVolume() {
    	this.controllerList.forEach(contr -> {
    		contr.updateVolume();
    	});
    }
}
