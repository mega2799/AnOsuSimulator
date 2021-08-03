package it.unibo.osu.model;

public enum GamePoints {
	OK(50),
	GREAT(300),
	PERFECT(300);
	
	private int value;
	
	GamePoints(int value){
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
