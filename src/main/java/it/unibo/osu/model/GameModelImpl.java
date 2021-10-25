package it.unibo.osu.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.osu.controller.HitActionObserver;
import it.unibo.osu.controller.Observer;
import it.unibo.osu.controller.ScoreManagerImpl;
import it.unibo.osu.util.Clock;
import it.unibo.osu.util.HitobjectSelector;

/**
 * The Class GameModelImpl implementation of {@link GameModel}.
 */
public class GameModelImpl implements Observer, HitActionObserver, GameModel {

    private GameStatus status;

    private BeatMap beatMap;

    private ScoreManagerImpl scoreManager;

    private LifeBar lifeBar;

    private List<HitpointImpl> currentHitbuttons;

    private HitobjectSelector selector;

    private Clock osuClock;

    private double timeAcc;

    /**
     * Instantiates a new game model impl.
     *
     * @param name the song name
     */
    public GameModelImpl(final String name) {
        this.status = GameStatus.START;
        this.beatMap = new BeatMapImpl(name);
        this.osuClock = new Clock();
        this.scoreManager = new ScoreManagerImpl(new ScoreImpl());
        this.timeAcc = 0;
    }

    @Override
    public void initGameOnStart() {
        this.lifeBar = new LifeBarImpl(this.beatMap.getHpDrainRate());
        this.currentHitbuttons = new ArrayList<>();
        this.selector = new HitobjectSelector(this.beatMap.getHitpoints());
        this.status = GameStatus.RUNNING;
        this.osuClock.start();
    }

    @Override
    public void update(final double t) {
        this.timeAcc += t;
        this.currentHitbuttons.addAll(this.selector.nextHitobjects(t));
        if (this.isDrainable()) {
            this.lifeBar.drain();
        }
    }

    @Override
    public void setPause() {
        if (this.status.equals(GameStatus.RUNNING)) {
            this.status = GameStatus.PAUSE;
            this.osuClock.pause();
        } else if (this.status.equals(GameStatus.PAUSE)) {
            this.status = GameStatus.RUNNING;
            this.osuClock.start();
        }
    }

    @Override
    public void buttonMissed() {
        this.lifeBar.lostLife();
        this.scoreManager.missed();
    }

    @Override
    public void buttonHitted(final GamePoints gamePoints) {
        this.lifeBar.gainLife(gamePoints);
        this.scoreManager.hit(gamePoints);
    }

    @Override
    public GameStatus getStatus() {
        return status;
    }

    @Override
    public BeatMap getBeatMap() {
        return beatMap;
    }

    @Override
    public ScoreManagerImpl getScoreManager() {
        return this.scoreManager;
    }

    @Override
    public List<HitpointImpl> getCurrentHitbuttons() {
        return currentHitbuttons;
    }

    @Override
    public final Clock getOsuClock() {
        return osuClock;
    }

    @Override
    public void clearCurrentHitbuttons(
            final List<HitpointImpl> currentHitbuttons) {
        this.currentHitbuttons.clear();
    }

    @Override
    public boolean isGameOver() {
        if (this.lifeBar.getHp() <= 0) {
            this.status = GameStatus.ENDGAME;
            return true;
        }
        return false;
    }

    @Override
    public void onNotify() {
        this.status = GameStatus.ENDGAME;
    }

    @Override
    public LifeBar getLifeBar() {
        return this.lifeBar;
    }

    /**
     * Checks if is drainable.
     *
     * @return true, if is drainable
     */
    private boolean isDrainable() {
        if (this.timeAcc < this.beatMap.getStartingTime()) {
            return false;
        } else {
            for (List<Double> breakTime : this.beatMap.getBreakTimes()) {
                if (this.timeAcc >= breakTime.get(0)
                        && this.timeAcc <= breakTime.get(1)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onNotify(final GamePoints points) {
        switch (points) {
        case MISS:
            this.buttonMissed();
            break;
        default:
            this.buttonHitted(points);
        }
    }

}
