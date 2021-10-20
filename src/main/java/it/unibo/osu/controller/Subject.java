package it.unibo.osu.controller;

public interface Subject  {
	void notifyObs();
	void addObserver(Observer obs);
	void removeObserver(Observer obs);
}
