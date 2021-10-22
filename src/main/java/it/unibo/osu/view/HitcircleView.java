package it.unibo.osu.view;

import java.util.List;

import it.unibo.osu.model.GameModelImpl;
import javafx.animation.ParallelTransition;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public interface HitcircleView {
	
	/**
	 * Gets the childrens as a {@link List}.
	 *
	 * @return the children
	 */
	List<Circle> getChildren();
	
	/**
	 * Gets the {@link ParallelTransition}.
	 *
	 * @return the parallel transition
	 */
	ParallelTransition getParallelTransition();
	
	/**
	 * Sets the input handlers.
	 */
	void setInputHandlers();
}
