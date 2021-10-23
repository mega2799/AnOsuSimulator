package it.unibo.osu.view;

import java.util.List;

import javafx.animation.ParallelTransition;
import javafx.scene.shape.Circle;

/**
 * The Interface HitcircleView.
 */
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
