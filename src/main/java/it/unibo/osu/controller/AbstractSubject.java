package it.unibo.osu.controller;

/**
 * The Class AbstractSubject.
 */
public abstract class AbstractSubject implements Subject {

    /**
     * Notify obs.
     */
    @Override
    abstract public void notifyObs();

    /**
     * Adds the observer.
     *
     * @param obs the obs
     */
    @Override
    public void addObserver(Observer obs) {
    }

    /**
     * Removes the observer.
     *
     * @param obs the obs
     */
    @Override
    public void removeObserver(Observer obs) {
    }

}
