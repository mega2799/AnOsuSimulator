package it.unibo.osu.model;

import it.unibo.osu.controller.HitActionObserver;

// da implementare
//public class LifeBar implements Updatable, HitActionObserver {
public class LifeBarImpl implements Updatable, LifeBar{

	private double hpDrainRate;
	private double hp;
	private static final double MAX_HEALTH_INCREASE = 5;
	
	public LifeBarImpl(double hpDrainRate) {
		this.hpDrainRate = hpDrainRate;
		this.hp = LifeBar.MAXHP;
	}
	
	@Override
	public void lostLife() {
		this.hp -= LifeBarImpl.MAX_HEALTH_INCREASE;
	}

	@Override
	public void gainLife(GamePoints gamePoints) {
		double hpValue;
		switch(gamePoints) {
		case MISS:
			break;
		case OK:
			hpValue = (this.hp + LifeBarImpl.MAX_HEALTH_INCREASE * 0.5);
			this.addHp(hpValue);
			break;
		case GREAT:
			hpValue = (this.hp +  LifeBarImpl.MAX_HEALTH_INCREASE);
			this.addHp(hpValue);
			break;
		case PERFECT:
			hpValue = (this.hp + LifeBarImpl.MAX_HEALTH_INCREASE * 1.05);
			this.addHp(hpValue);
			break;
		}
		
	}
	private void addHp(double hpValue) {
		this.hp = (this.hp + hpValue) > MAXHP ? MAXHP : (this.hp + hpValue);
	}
	
	@Override
	public void drain() {
		// /60 che sono gli fps del gioco cosi da togliere hpdrainrate al secondo forse si puo fare meglio.
		this.hp -= hpDrainRate/60;
	}

	@Override
	public double getHp() {
		return this.hp;
	}

	@Override
	public void update() {
		this.drain();
	}

//	@Override
//	public void onNotify(GamePoints points) {
//		switch (points) {
//		case MISS:
//			this.lostLife();
//			break;
//		default:
//			this.gainLife(points);
//			break;
//		}
//	}
	
}