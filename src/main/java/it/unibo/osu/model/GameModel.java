package it.unibo.osu.model;


import java.util.ArrayList;
import java.util.List;
import it.unibo.osu.controller.Observer;
import it.unibo.osu.controller.ScoreManager;
import it.unibo.osu.util.Clock;
import it.unibo.osu.util.HitobjectSelector;

public class GameModel implements Observer{
	private GameStatus status;
	private BeatMap beatMap;
	private ScoreManager scoreManager;
	private LifeBar lifeBar;
	private List<Hitpoint> currentHitbuttons;
	private HitobjectSelector selector;
	private Clock osuClock;
	private double timeAcc;
 


	public GameModel(final String name) {
		this.status = GameStatus.START;
		this.beatMap = new BeatMap(name);
		this.osuClock = new Clock();
		this.scoreManager = new ScoreManager(new Score());
		this.timeAcc = 0;
	}
	
	public void initGameOnStart() {
//		this.scoreManager = new ScoreManager(new Score());
		this.lifeBar = new LifeBar(this.beatMap.getHpDrainRate());
		this.currentHitbuttons = new ArrayList<>();
		this.selector = new HitobjectSelector(this.beatMap.getHitpoints());
		this.status = GameStatus.RUNNING;
		this.osuClock.start();
	}
	
	public void update(double t) {
		this.timeAcc += t;
		this.currentHitbuttons.addAll(this.selector.nextHitobjects(t));
		//this.lifeBar.drain();   // ricorda di scommentare/commentare per testare game
		if(this.isDrainable()) {
			this.lifeBar.drain();
		}
	}
	
    public void setPause() {
        if (this.status.equals(GameStatus.RUNNING)) {
            this.status = GameStatus.PAUSE;
            this.osuClock.pause();
        } else if (this.status.equals(GameStatus.PAUSE)) {
            this.status = GameStatus.RUNNING;
            this.osuClock.start();
        }
    }
    
    public void buttonMissed() {
    	this.lifeBar.lostLife();
    	this.scoreManager.missed();
    	
    }
    
    public void buttonHitted(GamePoints gamePoints) {
    	this.lifeBar.gainLife(gamePoints);
    	this.scoreManager.hitted(gamePoints);
    }
    
	public GameStatus getStatus() {
		return status;
	}

	public BeatMap getBeatMap() {
		return beatMap;
	}

	public ScoreManager getScoreManager() {
		return this.scoreManager;
	}

	public List<Hitpoint> getCurrentHitbuttons() {
		return currentHitbuttons;
	}

	public final Clock getOsuClock() {
		return osuClock;
	}

	public void clearCurrentHitbuttons(List<Hitpoint> currentHitbuttons) {
		this.currentHitbuttons.clear();
	}
	
	//da implementare
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

}
