package it.unibo.osu.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;

// TODO: Auto-generated Javadoc
/**
 * The Interface Statistic.
 */
public interface Statistic {
	
	/**
	 * Adds the player.
	 *
	 * @param player the player
	 */
	void addPlayer(String player);
	
	/**
	 * Gets the player history.
	 *
	 * @param player the player
	 * @return the player history
	 */
	List<String> getPlayerHistory(String player);
	
	/**
	 * Adds the result of the game to the player.
	 *
	 * @param player the player
	 * @param points the points
	 */
	void addResult(String player, String points);

	/**
	 * Read json file that contains the statistic to import it as a {@link Map} that contains the 
	 * player's name and a list of the previous game score.
	 *
	 * @throws JsonParseException the json parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void readJson() throws JsonParseException, IOException;

	/**
	 * Write in a .json the score of the last finished game, for the current player.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void writeJson() throws IOException;
}
