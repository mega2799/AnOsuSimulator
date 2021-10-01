package it.unibo.osu.controller;

import it.unibo.osu.model.GamePoints;

public interface HitActionSubject {
	public void notifyObs(GamePoints points);
	public void addObserver(HitActionObserver obs);
	public void removeObserver(HitActionObserver obs);
}
