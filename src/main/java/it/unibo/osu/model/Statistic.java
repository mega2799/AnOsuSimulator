package it.unibo.osu.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;

public interface Statistic {
	void addPlayer(String player);
	
	List<Integer> getPlayerHistory(String player);
	
	void addResult(String player, int points);

	void setSong(String song);

	void readJson() throws JsonParseException, IOException;

	void writeJson() throws IOException;
}
