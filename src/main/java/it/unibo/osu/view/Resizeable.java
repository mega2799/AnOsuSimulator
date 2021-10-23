package it.unibo.osu.view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

/**
 * The Class Resizeable.
 */
public abstract class Resizeable {
	
	/**
	 * Change resolution of the current {@link Pane} scaling all the elements.
	 *
	 * @param pane the pane
	 * @param width the width
	 * @param height the height
	 */
	public void changeResolution(Pane pane,double width,double height) {
		final Scale scale = new Scale(width/pane.getPrefWidth(),height/pane.getPrefHeight());
		pane.getTransforms().add(scale);
		pane.setPrefHeight(height);
		pane.setPrefWidth(width);
	}
}
