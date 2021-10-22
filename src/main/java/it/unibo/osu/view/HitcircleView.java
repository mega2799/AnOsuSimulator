package it.unibo.osu.view;

import java.util.List;

import it.unibo.osu.model.GameModelImpl;
import javafx.animation.ParallelTransition;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public interface HitcircleView {
	public List<Circle> getChildren();
	public ParallelTransition getParallelTransition();
	public void setInputHandlers();
}
