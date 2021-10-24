package it.unibo.osu.controller;

import it.unibo.osu.model.GamePoints;

/**
 * An asynchronous update interface for receiving notifications about HitAction
 * information as the HitAction is constructed.
 */
public interface HitActionObserver {

    /**
     * This method is called when information about an HitAction which was
     * previously requested using an asynchronous interface becomes available.
     *
     * @param points the points
     */
    void onNotify(GamePoints points);

}
