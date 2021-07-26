package it.unibo.osu.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BeatmapReader extends BufferedReader{
	private List<SpaceTimeCoord> hitpoints;
	private Integer n;
	private int index = 0;
	private List<String> lines;
	
	public BeatmapReader(FileReader in)  {
		super(in);
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
		this.hitpoints = lines.stream().skip(n.intValue())
				.takeWhile(x -> x.equals("n"))
				.map((x)-> {
					String[] values = x.split(",");
					return new SpaceTimeCoord(Double.parseDouble(values[0]), Double.parseDouble(values[1]),Double.parseDouble(values[2]));
				})
				.collect(Collectors.toList());
	}
	
	public  List<SpaceTimeCoord> getHitpoints(){
		return this.hitpoints;
	}
	
	public Stream<SpaceTimeCoord> hitPointsStream() {
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
	public SpaceTimeCoord readHitpoint() {
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
	
}
