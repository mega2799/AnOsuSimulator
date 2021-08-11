package it.unibo.osu.model;

// da implementare
public class LifeBar implements Updatable {

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
		this.hp -= hpDrainRate;
	}

	public double getHp() {
		return this.hp;
	}

	@Override
	public void update() {
		this.drain();
	}
	
}
