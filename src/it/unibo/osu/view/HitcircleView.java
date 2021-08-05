package it.unibo.osu.view;

import java.util.List;

import it.unibo.osu.model.GameModel;
import javafx.animation.ParallelTransition;

import javafx.scene.image.ImageView;

public interface HitcircleView {
	public List<ImageView> getChildren();
	public ParallelTransition getParallelTransition();
	public void setInputHandlers(GameModel game);
}
