package it.unibo.osu.model;

/**
 * The Class LifeBarImpl implementation of {@link LifeBar}.
 */
public class LifeBarImpl implements Updatable, LifeBar {

    private double hpDrainRate;
    private double hp;
    private static final double MAX_HEALTH_INCREASE = 5;

    /**
     * Instantiates a new life bar impl.
     *
     * @param hpDrainRate the hp drain rate
     */
    public LifeBarImpl(final double hpDrainRate) {
        this.hpDrainRate = hpDrainRate;
        this.hp = LifeBar.MAXHP;
    }

    @Override
    public void lostLife() {
        this.hp -= LifeBarImpl.MAX_HEALTH_INCREASE;
    }

    @Override
    public void gainLife(final GamePoints gamePoints) {
        double hpValue;
        switch (gamePoints) {
        case MISS:
            break;
        case OK:
            hpValue = (this.hp + LifeBarImpl.MAX_HEALTH_INCREASE * 0.5);
            this.addHp(hpValue);
            break;
        case GREAT:
            hpValue = (this.hp + LifeBarImpl.MAX_HEALTH_INCREASE);
            this.addHp(hpValue);
            break;
        case PERFECT:
            hpValue = (this.hp + LifeBarImpl.MAX_HEALTH_INCREASE * 1.05);
            this.addHp(hpValue);
            break;
        }

    }

    private void addHp(final double hpValue) {
        this.hp = (this.hp + hpValue) > MAXHP ? MAXHP : (this.hp + hpValue);
    }

    @Override
    public void drain() {
        this.hp -= hpDrainRate / 60;
    }

    @Override
    public double getHp() {
        return this.hp;
    }

    @Override
    public void update() {
        this.drain();
    }

}