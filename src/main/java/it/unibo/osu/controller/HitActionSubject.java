package it.unibo.osu.controller;

import it.unibo.osu.model.GamePoints;

/**
 * The Interface HitActionSubject.
 */
public interface HitActionSubject {

    /**
     * Notify obs.
     *
     * @param points the points
     */
    void notifyObs(GamePoints points);

    /**
     * Adds the observer.
     *
     * @param obs the obs
     */
    void addObserver(HitActionObserver obs);

    /**
     * Removes the observer.
     *
     * @param obs the obs
     */
    void removeObserver(HitActionObserver obs);

}
