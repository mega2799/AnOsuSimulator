package it.unibo.osu.controller;

import it.unibo.osu.model.GamePoints;

public interface HitActionObserver {
	public void onNotify(GamePoints points);
}
