package it.unibo.osu.model;

import java.util.List;

public interface Statistic {
	void addPlayer(String player);
	
	List<Integer> getPlayerHistory(String player);
	
	void addResult(String player, int points);

	void setSong(String song);

	void writeJson();
}
