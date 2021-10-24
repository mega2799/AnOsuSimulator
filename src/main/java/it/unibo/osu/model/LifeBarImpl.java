package it.unibo.osu.model;

/**
 * The Class LifeBarImpl implementation of {@link LifeBar}.
 */
public class LifeBarImpl implements Updatable, LifeBar {

    public static final double OK_INCREASE_RATE = 0.5;
    public static final double PERFECT_INCREASE_RATE = 1.05;
    public static final int FPS = 60;
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
            hpValue = (this.hp + LifeBarImpl.MAX_HEALTH_INCREASE
                * OK_INCREASE_RATE);
            this.addHp(hpValue);

            break;
        case GREAT:
            hpValue = (this.hp + LifeBarImpl.MAX_HEALTH_INCREASE);
            this.addHp(hpValue);
            break;
        case PERFECT:
            hpValue = (this.hp + LifeBarImpl.MAX_HEALTH_INCREASE
                * PERFECT_INCREASE_RATE);
            this.addHp(hpValue);
            break;
        default:
            break;

        }

    }

    private void addHp(final double hpValue) {
        this.hp = Math.min((this.hp + hpValue), MAXHP);
    }

    @Override
    public void drain() {
        this.hp -= hpDrainRate / FPS;
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