package it.unibo.osu.controller;

import it.unibo.osu.model.GamePoints;

public interface HitActionSubject {
	void notifyObs(GamePoints points);
	void addObserver(HitActionObserver obs);
	void removeObserver(HitActionObserver obs);
}
