package it.unibo.osu.controller;

/**
 * The Interface Subject.
 */
public interface Subject {

    /**
     * Notify obs.
     */
    void notifyObs();

    /**
     * Adds the observer.
     *
     * @param obs the obs
     */
    void addObserver(Observer obs);

    /**
     * Removes the observer.
     *
     * @param obs the obs
     */
    void removeObserver(Observer obs);
}
