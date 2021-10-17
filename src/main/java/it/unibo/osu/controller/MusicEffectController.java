package it.unibo.osu.controller;

import it.unibo.osu.model.GamePoints;

public class MusicEffectController implements HitActionObserver {
	private MusicControllerImpl hitEffect;
	private MusicControllerImpl missEffect;
	
	public MusicEffectController(String hitEffect, String missEffect) {
		this.hitEffect = MusicControllerImplFactory.getEffectImpl(hitEffect);
		this.missEffect = MusicControllerImplFactory.getEffectImpl(missEffect);
	}
	@Override
	public void onNotify(GamePoints points) {
		if(points.equals(GamePoints.MISS)) {
			missEffect.onNotify();
		} else {
			hitEffect.onNotify();
		}
	}

}
