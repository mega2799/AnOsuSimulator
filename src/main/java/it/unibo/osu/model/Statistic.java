package it.unibo.osu.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;

public interface Statistic {
	void addPlayer(String player);
	
	List<String> getPlayerHistory(String player);
	
	void addResult(String player, String points);

	void setSong(String song);

	void readJson() throws JsonParseException, IOException;

	void writeJson() throws IOException;
}
