package it.unibo.osu.Model;

public enum GamePoints {
	GOOD(50),
	GREAT(100),
	PERFECT(300);
	
	private int value;
	
	GamePoints(int value){
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
