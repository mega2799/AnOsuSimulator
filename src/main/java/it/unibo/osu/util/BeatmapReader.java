package it.unibo.osu.util;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.osu.model.HitPoint;
import it.unibo.osu.model.HitpointImpl;



public class BeatmapReader extends BufferedReader{
	private List<HitpointImpl> hitpoints;
	private Integer n;
	private int index = 0;
	private List<String> lines;
	
	public BeatmapReader(InputStream in)  {
		super(new InputStreamReader(in));
		this.lines = super.lines().collect(Collectors.toList());
		this.setHitpoints();
	}
	
	private void setHitpoints() {
		try {
			this.n = findNumOfLinesToOptions(this.lines,BeatmapOptions.HITOBJECTS);
		} catch (IOException e) {
			System.out.println("Error: stringa \"[HitObjects]\" non presente nella beatmap!");
			e.printStackTrace();
		}
		this.hitpoints = lines.stream()
				.skip(n.intValue())
				.filter(x -> !x.contains("//"))
				.takeWhile(x -> !x.equals(""))
				.map((x)-> {
					String[] values = x.split(",");
					return new HitpointImpl(Double.parseDouble(values[0]), Double.parseDouble(values[1]),Double.parseDouble(values[2]));
				})
				.collect(Collectors.toList());
	}
	
	public  List<HitpointImpl> getHitpoints(){
		return this.hitpoints;
	}
	
	public Stream<HitpointImpl> hitPointsStream() {
		return this.hitpoints.stream();
	}
	
	private boolean hasNextHitpoint() {
		if(this.hitpoints.get(index+1)!=null) {
			return true;
		} else {
			this.resetIndex(); 
			return false;
		}
	}
	public HitPoint readHitpoint() {
		if(this.hasNextHitpoint()) {
			index += 1;
			return this.hitpoints.get(index);
		} else {
			return null;
		}
	}
	
	public void resetIndex() {
		this.index = 0;
	}
	
	private int findNumOfLinesToOptions(List<String> lines,BeatmapOptions opt) throws IOException  {
			int count = 0;
			boolean flagFound = false;
			for(String line: lines) {
				//System.out.println(line);
				count += 1;
				if(line.contains(opt.getValue())){
					flagFound = true;
					break;
				}
			}
			if(flagFound) {
				return count;
			} else {
				throw new IOException();
			}
	}
	
	//solo per retribuire elementi separati da ": " non per BeatmapOptions con la "," . quindi non posso usare 
	// questo metodo per [events],[timingpoints],[hitobjects], vanno gestiti nel caso come ho fatto con
	//gli hitobjects -> vedere set hitpoints
	// guardando gli unici utili potrebbero essere hitobjects e difficulty
	public HashMap<String, String>  getOptionMap(BeatmapOptions opt) throws IOException {
		this.n = findNumOfLinesToOptions(this.lines, opt);
		HashMap<String,String> map =  (HashMap<String, String>) this.lines.stream()
				.skip(this.n)
				.filter(x -> !x.contains("//"))
				.takeWhile(x -> !x.equals(""))
				.map(x ->  Arrays.asList(x.split(":")) )
				//sostituire . con ,?
				.collect(Collectors.toMap(x -> x.get(0), x ->  x.get(1)));
			return map;
	}
	
	public String getBakground() {
		try {
			this.n = findNumOfLinesToOptions(this.lines,BeatmapOptions.EVENTS);
		} catch (IOException e) {
			System.out.println("Error: stringa \"[Events]\" non presente nella beatmap!");
			e.printStackTrace();
		}
		return this.lines.stream()
				.skip(this.n)
				.filter(x -> !x.contains("//"))
				.takeWhile(x -> !x.equals(""))
				.map((x)-> {
					String[] values = x.split(",");
					return Arrays.asList(values).get(2);})
				.collect(Collectors.toList()).get(0).replaceAll("\"", "");
	}
	
	public Double getStartingTime(){
		try {
			this.n = findNumOfLinesToOptions(this.lines,BeatmapOptions.TIMINGPOINTS);
		} catch (IOException e) {
			System.out.println("Error: stringa \"[Events]\" non presente nella beatmap!");
			e.printStackTrace();
		}
		  Double returnValue =  this.lines.stream().skip(this.n)
				  .limit(1)
				  .map(x -> {
					  return Double.parseDouble(Arrays.asList(x.split(",")).get(0));
				  }).reduce((x,y) -> x + y).get();
		  if(returnValue == null) {
			  System.out.println("Missing starting time.");
			  throw new RuntimeException();
		  } else {
			  return returnValue;
		  }
	}

	public List<List<Double>> getBreakTimes(){
		try {
			this.n = findNumOfLinesToOptions(this.lines,BeatmapOptions.EVENTS);
		} catch (IOException e) {
			System.out.println("Error: stringa \"[Events]\" non presente nella beatmap!");
			e.printStackTrace();
		}
		return this.lines.stream()
				.skip(this.n)
				.filter(x -> !x.contains("//"))
				.takeWhile(x -> !x.equals(""))
				.filter(x -> x.split(",")[0].equals("2"))
				.map((x)-> {
					String[] values = x.split(",");
					return Arrays.asList(values).subList(1, 3).stream()
							.map(y -> Double.parseDouble(y))
							.collect(Collectors.toList());})
				.collect(Collectors.toList());
	}
}

