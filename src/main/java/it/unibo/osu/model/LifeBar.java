package it.unibo.osu.model;

public interface LifeBar {

	double MAXHP = 100;

	void lostLife();

	void gainLife(GamePoints gamePoints);

	void drain();

	double getHp();

	void update();

}