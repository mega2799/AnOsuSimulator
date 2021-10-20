package it.unibo.osu.controller;

public abstract class AbstractSubject implements Subject {

	@Override
	abstract public void notifyObs();

	@Override
	public void addObserver(Observer obs) {
	}

	@Override
	public void removeObserver(Observer obs) {
	}

}
