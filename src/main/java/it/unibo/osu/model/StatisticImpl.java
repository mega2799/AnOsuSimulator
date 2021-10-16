package it.unibo.osu.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;

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
		if (!this.map.containsKey(player)) {
			this.map.put(player, new ArrayList<>());
		}
	}

	
	
	
	
	@Override
	public void writeJson() throws IOException {
		JsonFactory jF = new JsonFactory();
		JsonGenerator jG;

		jG = jF.createGenerator(new File("users.json"), JsonEncoding.UTF8);
		jG.useDefaultPrettyPrinter();

		jG.writeStartObject();
		this.map.forEach((k, v) -> {
			try {
				//jG.writeStringField(k, v.toString());
				jG.writeFieldName(k);
				//int[] arr = v.stream().mapToInt(i -> i).toArray();
				//jG.writeArray(arr, 0, arr.length);
				//jG.writeString(v.toString());
				jG.writeString(List.of(3 ,12, 1).toString());
				//jG.writeNumber(6666);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		jG.close();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void readJson() throws JsonParseException, IOException {
		// TODO Auto-generated method stub
		JsonFactory jF = new JsonFactory();
		JsonParser jP = jF.createJsonParser(new File("users.json"));
		
		  // Sanity check: verify that we got "Json Object":
		if (jP.nextToken() != JsonToken.START_OBJECT) {
		    throw new IOException("Expected data to start with an Object");
		  }
		//jP.getCodec().readValue(jP, int[].class);
		  while (jP.nextToken() != JsonToken.END_OBJECT) {
			  String fieldName = jP.getCurrentName();
			  String value = jP.getValueAsString();
			  List<String> l = Arrays.asList(value.substring(1, value.length() - 1).split(", "));
			  System.out.println(fieldName + ":" + l);
		  }
	}

}
