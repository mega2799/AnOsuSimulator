package it.unibo.osu.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticImpl implements Statistic {

	private static final Statistic STATISTIC = new StatisticImpl();

	private String song = null;

	private Map<String, List<Integer>> map = new HashMap<>();

	@Override
	public final void setSong(String song) {
		this.song = song;
	}

	public static final Statistic getStat() {
		return STATISTIC;
	}

	@Override
	public List<Integer> getPlayerHistory(String player) {
		return this.map.get(player);
	}

	@Override
	public void addResult(String player, int points) {
		if (this.song != null) {
			this.map.get(player).add(points);
		}
	}

	@Override
	public void addPlayer(String player) {
		this.map.put(player, new ArrayList<>());
	}

	@Override
	public void writeJson() {
		/*
		ObjectMapper objM = new ObjectMapper();
			try {
				String json = objM.writeValueAsString(Map.of(this.song, this.map));
				System.out.println(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
	}

}
