package it.unibo.osu.model;


import java.util.ArrayList;
import java.util.List;

import it.unibo.osu.controller.Observer;
import it.unibo.osu.controller.ScoreManager;
import it.unibo.osu.util.HitobjectSelector;
import it.unibo.osu.util.SpaceTimeCoord;

public class GameModel implements Observer{
	private GameStatus status;
	private BeatMap beatMap;
	private ScoreManager scoreManager;
	private LifeBar lifeBar;
	private List<SpaceTimeCoord> currentHitbuttons;
	private HitobjectSelector selector;
	private double timeAcc;
	
	public GameModel(final String name) {
		this.status = GameStatus.START;
		this.beatMap = new BeatMap(name);
		this.timeAcc = 0;
	}
	
	public void initGameOnStart() {
		this.scoreManager = new ScoreManager(new Score());
		this.lifeBar = new LifeBar(this.beatMap.getHpDrainRate());
		this.currentHitbuttons = new ArrayList<>();
		this.selector = new HitobjectSelector(this.beatMap.getHitpoints());
		this.status = GameStatus.RUNNING;
	}
	
	public void update(double t) {
		this.currentHitbuttons.addAll(this.selector.nextHitobjects(t));
		this.lifeBar.drain();
		
		//il render fara` il clear della lista currentHitObjects
		//per debug quanto segue da togliere
//		if(!this.currentHitbuttons.equals(List.of())) {
//			System.out.println(this.currentHitbuttons);
//			this.currentHitbuttons.clear();
//		}
		//
	}
	
    public void setPause() {
        if (this.status.equals(GameStatus.RUNNING)) {
            this.status = GameStatus.PAUSE;
        } else if (this.status.equals(GameStatus.PAUSE)) {
            this.status = GameStatus.RUNNING;
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

	public Score getScore() {
		return this.scoreManager.getScore();
	}

	public List<SpaceTimeCoord> getCurrentHitbuttons() {
		return currentHitbuttons;
	}

	public void clearCurrentHitbuttons(List<SpaceTimeCoord> currentHitbuttons) {
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

	
 
}
