package it.unibo.osu.model;


import java.util.ArrayList;
import java.util.List;

import it.unibo.osu.controller.HitActionObserver;
import it.unibo.osu.controller.MusicControllerImpl;
import it.unibo.osu.controller.MusicControllerImplFactory;
import it.unibo.osu.controller.Observer;
import it.unibo.osu.controller.ScoreManagerImpl;
import it.unibo.osu.util.Clock;
import it.unibo.osu.util.HitobjectSelector;

public class GameModelImpl implements Observer, HitActionObserver, GameModel{
	private GameStatus status;
	private BeatMap beatMap;
	private ScoreManagerImpl scoreManager;
	private LifeBar lifeBar;
	private List<HitpointImpl> currentHitbuttons;
	private HitobjectSelector selector;
	private Clock osuClock;
	private double timeAcc;


	public GameModelImpl(final String name) {
		this.status = GameStatus.START;
		this.beatMap = new BeatMapImpl(name);
		this.osuClock = new Clock();
		this.scoreManager = new ScoreManagerImpl(new ScoreImpl());
		this.timeAcc = 0;
	}
	
	@Override
	public void initGameOnStart() {
//		this.scoreManager = new ScoreManager(new Score());
		this.lifeBar = new LifeBarImpl(this.beatMap.getHpDrainRate());
		this.currentHitbuttons = new ArrayList<>();
		this.selector = new HitobjectSelector(this.beatMap.getHitpoints());
		this.status = GameStatus.RUNNING;
		this.osuClock.start();
	}
	
	@Override
	public void update(double t) {
		this.timeAcc += t;
		this.currentHitbuttons.addAll(this.selector.nextHitobjects(t));
		//this.lifeBar.drain();   // ricorda di scommentare/commentare per testare game
		if(this.isDrainable()) {
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
	public void buttonHitted(GamePoints gamePoints) {
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
	public void clearCurrentHitbuttons(List<HitpointImpl> currentHitbuttons) {
		this.currentHitbuttons.clear();
	}
	
	//da implementare
	@Override
	public boolean isGameOver() {
		if(this.lifeBar.getHp() <= 0) {
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
	
	private boolean isDrainable() {
		if(this.timeAcc < this.beatMap.getStartingTime()){
			return false;
		} else {
			for(List<Double> breakTime: this.beatMap.getBreakTimes()) {
				if( this.timeAcc >= breakTime.get(0) && this.timeAcc <= breakTime.get(1)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void onNotify(GamePoints points) {
		switch (points) {
		case MISS:
			this.buttonMissed();
			break;
		default:
			this.buttonHitted(points);
		}
	}

}
