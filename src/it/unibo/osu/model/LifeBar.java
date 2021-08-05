package it.unibo.osu.model;

import it.unibo.osu.controller.HitActionObserver;

// da implementare
public class LifeBar implements Updatable, HitActionObserver {

	private double hpDrainRate;
	private double hp;
	public static final  double MAXHP = 100;
	private static final double MAX_HEALTH_INCREASE = 5;
	
	public LifeBar(double hpDrainRate) {
		this.hpDrainRate = hpDrainRate;
		this.hp = LifeBar.MAXHP;
	}
	
	public void lostLife() {
		this.hp -= LifeBar.MAX_HEALTH_INCREASE;
	}

	public void gainLife(GamePoints gamePoints) {
		switch(gamePoints) {
		case OK:
			this.hp -= LifeBar.MAX_HEALTH_INCREASE * 0.5;
			break;
		case GREAT:
			this.hp += LifeBar.MAX_HEALTH_INCREASE;
			break;
		case PERFECT:
			this.hp -= LifeBar.MAX_HEALTH_INCREASE * 1.05;
			break;
		}
		
	}
	
	public void drain() {
		// /60 che sono gli fps del gioco cosi da togliere hpdrainrate al secondo
		this.hp -= hpDrainRate/60;
	}

	public double getHp() {
		return this.hp;
	}

	@Override
	public void update() {
		this.drain();
	}

	@Override
	public void onNotify(GamePoints points) {
		switch (points) {
		case MISS:
			this.lostLife();
			break;
		default:
			this.gainLife(points);
			break;
		}
	}
	
}
