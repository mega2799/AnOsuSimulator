package it.unibo.osu.view;

import java.util.List;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.ImageView;

public interface HitcircleView {
	public List<ImageView> getChildren();
	public ParallelTransition getParallelTransition();
}
