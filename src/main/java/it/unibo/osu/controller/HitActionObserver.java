package it.unibo.osu.controller;

import it.unibo.osu.model.GamePoints;

public interface HitActionObserver {
	void onNotify(GamePoints points);
}
