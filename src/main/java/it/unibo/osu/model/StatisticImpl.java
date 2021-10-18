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

	private Map<String, List<String>> map = new HashMap<>();

	@Override
	public final void setSong(String song) {
		this.song = song;
	}

	public static final Statistic getStat() {
		return STATISTIC;
	}

	@Override
	public List<String> getPlayerHistory(String player) {
		return this.map.get(player);
	}

	@Override
	public void addResult(String player, String points) {
  	if (!this.map.containsKey(player)) {
    		if (this.map.get(player) == null) {
    			this.map.put(player, new ArrayList<String>());
    		}
  	}
  	this.map.get(player).add(points);
	}

	@Override
	public void addPlayer(String player) {
		if (!this.map.containsKey(player)) {
			this.map.put(player, new ArrayList<String>());
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
				//jG.writeString("NomeSong");
				//jG.writeFieldName(k);

				//jG.writeStringField(this.song, k);
				jG.writeStringField(k, v.toString());
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
		File f = new File("users.json");
		JsonParser jP = jF.createJsonParser(f);
		  // Sanity check: verify that we got "Json Object":
		if (jP.nextToken() != JsonToken.START_OBJECT) {
		    throw new IOException("Expected data to start with an Object");
		  }
		//jP.getCodec().readValue(jP, int[].class);
		  while (jP.nextToken() != JsonToken.END_OBJECT) {
			  //String fieldName = jP.getCurrentName();
			  //String value = jP.getValueAsString();
			  
			  String player = jP.getCurrentName();
			  String pointList = jP.getValueAsString();
			  
			  //List<String> l = Arrays.asList(pointList.substring(1, pointList.length() - 1).split(", "));
			  List<String> l = new ArrayList<String>(Arrays.asList(pointList.substring(1, pointList.length() - 1).split(", ")));
			  this.map.put(player, l);
		  }
		  //f.delete();
//		JsonFactory jF = new JsonFactory();
//		File f = new File("users.json");
//		JsonParser jP = jF.createJsonParser(f);
//		  // Sanity check: verify that we got "Json Object":
//		if (jP.nextToken() != JsonToken.START_OBJECT) {
//		    throw new IOException("Expected data to start with an Object");
//		  }
//		//jP.getCodec().readValue(jP, int[].class);
//		  while (jP.nextToken() != JsonToken.END_OBJECT) {
//			  //String fieldName = jP.getCurrentName();
//			  //String value = jP.getValueAsString();
//			  
//			  String player = jP.getCurrentName();
//			  String pointList = jP.getValueAsString();
//			  
//			  List<String> l = Arrays.asList(pointList.substring(1, pointList.length() - 1).split(", "));
//			  this.map.put(player, l);
//		  }
//		  f.delete();
	}

}
