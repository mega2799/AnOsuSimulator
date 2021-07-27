package it.unibo.osu.Model;


import it.unibo.osu.Controller.ScoreManager;

public class GameModel {
	private GameStatus status;
	private BeatMap beatMap;
	private ScoreManager scoreManager;
	private Score score;
	private int multiplier;
	private LifeBar lifeBar;
	
	public GameModel(final String name) {
		this.status = GameStatus.START;
		this.score = new Score();
		this.multiplier = 0;
		this.beatMap = new BeatMap(name);
		this.scoreManager = new ScoreManager(this.score);
	}
	
	public void initGameOnStart() {
		this.status = GameStatus.RUNNING;
		// TODO Auto-generated method stub
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
		return score;
	}


	public int getMultiplier() {
		return multiplier;
	}

 
}
