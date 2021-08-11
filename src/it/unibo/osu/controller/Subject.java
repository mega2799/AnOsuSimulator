package it.unibo.osu.controller;

public interface Subject {
	public void notifyObs();
	public void addObserver(Observer obs);
	public void removeObserver(Observer obs);
}
