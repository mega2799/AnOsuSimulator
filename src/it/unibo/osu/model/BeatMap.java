package it.unibo.osu.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import it.unibo.osu.util.*;
//da implementare usando un reader da file + parser 
public class BeatMap {

	//campi obbligatori quindi per ogni beatmap file
	private List<Hitpoint> hitpoints;
	private double hpDrainRate;
	private double circleSize;
	private double overallDifficulty;
	private double approachRate;
	private String background;   
	private String songName;
	
	public BeatMap(String filaName) {
		try(BeatmapReader reader = new BeatmapReader(new FileReader(new File(this.getClass().getResource(filaName).toURI())))){
			Map<String, String> map = reader.getOptionMap(BeatmapOptions.DIFFICULTY);
			this.circleSize = Double.parseDouble(map.get("CircleSize"));
			this.overallDifficulty = Double.parseDouble(map.get("OverallDifficulty"));
			this.approachRate = Double.parseDouble(map.get("ApproachRate"));
			this.hpDrainRate = Double.parseDouble(map.get("HPDrainRate"));
			this.hitpoints = reader.getHitpoints();
			this.background = reader.getBakground();
			this.songName = reader.getOptionMap(BeatmapOptions.GENERAL).get("AudioFilename");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		} 		
	}
	
	public List<Hitpoint> getHitpoints() {
		return hitpoints;
	}

	public double getHpDrainRate() {
		return hpDrainRate;
	}

	public double getCircleSize() {
		return circleSize;
	}

	public double getOverallDifficulty() {
		return overallDifficulty;
	}

	public double getApproachRate() {
		return approachRate;
	}
	
	public String getBackground() {
		return this.background;
	}

	public String getSongName() {
		return songName;
	}

}
