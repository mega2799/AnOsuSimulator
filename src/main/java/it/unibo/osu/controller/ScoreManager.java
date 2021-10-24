package it.unibo.osu.controller;

import java.util.Map;

import it.unibo.osu.model.GamePoints;
import it.unibo.osu.model.HitPoint;
import it.unibo.osu.model.Score;

public interface ScoreManager {

    /**
     * Hit is the function that is called when the user hit a {@link HitPoint}.
     *
     * @param points the points
     */
    void hit(GamePoints points);

    /**
     * missed is the function that is called when the user miss a {@link HitPoint}.
     */
    void missed();

    /**
     * @return the {@link Score}
     */
    Score getScore();

    /**
     * Sets the score.
     *
     * @param score the new score
     */
    void setScore(Score score);

    /**
     * Gets the points.
     *
     * @return the points
     */
    int getPoints();

    /**
     * Gets the multiplier.
     *
     * @return the multiplier
     */
    int getMultiplier();

    /**
     * Gets the statistic.
     *
     * @return the statistic
     */
    Map<GamePoints, Integer> getStatistic();

}