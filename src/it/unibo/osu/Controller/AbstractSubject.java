package it.unibo.osu.Controller;
import it.unibo.osu.Controller.Observer;
import it.unibo.osu.Controller.Subject;

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
