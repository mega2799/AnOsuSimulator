package it.unibo.osu.controller;
import it.unibo.osu.controller.Observer;
import it.unibo.osu.controller.Subject;

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
