package it.unibo.osu.view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public abstract class Resizeable {
	public void changeResolution(Pane pane,double width,double height) {
		Scale scale = new Scale(width/pane.getPrefWidth(),height/pane.getPrefHeight());
		pane.getTransforms().add(scale);
		pane.setPrefHeight(height);
		pane.setPrefWidth(width);
	}
}
