package Model;

import it.unibo.osu.Controller.MusicController;
import it.unibo.osu.Controller.MusicControllerImpl;
import it.unibo.osu.Controller.ScoreManager;

public class GameModel {
	private GameStatus status;
	private BeatMap beatMap;
	private ScoreManager scoreManager;
	private int score;
	private int multiplier;
	private LifeBar lifeBar;
	
	public GameModel(final String name) {
		this.status = GameStatus.START;
		this.score = 0;
		this.multiplier = 0;
		this.beatMap = new BeatMap(name);
		this.scoreManager = new ScoreManager(this);
	}
	
	
	public void update(double t) {
		// da implementare, dovrà tener conto di quanto passato nella song, e di stato di gioco che se è in pausa
		// non può procedere 
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
    	this.scoreManager.hitted();
    	
    }
    
    public void buttonHitted() {
    	this.lifeBar.gainLife();
    	this.scoreManager.missed();
    }
    
    
	public GameStatus getStatus() {
		return status;
	}


	public BeatMap getBeatMap() {
		return beatMap;
	}


	public int getScore() {
		return score;
	}


	public int getMultiplier() {
		return multiplier;
	}
    
    public void incScore(final int value) {
    	this.score += value;
    }
    
    public void setMultiplier(final int value) {
    	this.multiplier = value;
    }
 
}
