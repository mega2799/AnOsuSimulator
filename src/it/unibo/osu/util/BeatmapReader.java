package it.unibo.osu.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BeatmapReader extends BufferedReader{
	private List<SpaceTimeCoord> xyt;
	private Integer n;
	private int index = 0;
	private List<String> lines;
	
	public BeatmapReader(FileReader in)  {
		super(in);
		this.lines = super.lines().collect(Collectors.toList());
		if(this.n==null) {
			try {
				this.n = findNumOfLinesToHitcircle(this.lines);
			} catch (IOException e) {
				System.out.println("Error: stringa \"[HitObjects]\" non presente nella beatmap!");
				e.printStackTrace();
			}
		} 		
		this.xyt = lines.stream().skip(n.intValue()).map((x)-> {
					String[] values = x.split(",");
					return new SpaceTimeCoord(Double.parseDouble(values[0]), Double.parseDouble(values[1]),Double.parseDouble(values[2]));
				}).collect(Collectors.toList());
	}
	
	public  List<SpaceTimeCoord> getHitpoints(){
		return this.xyt;
	}
	
	public Stream<SpaceTimeCoord> hitPointsStream() {
		return this.xyt.stream();
	}
	
	private boolean hasNext() {
		if(this.xyt.get(index+1)!=null) {
			return true;
		} else {
			this.index = 0;
			return false;
		}
	}
	public SpaceTimeCoord readHitpoint() {
		if(this.hasNext()) {
			index += 1;
			return this.xyt.get(index);
		} else {
			return null;
		}
	}
	public void reset() {
		this.index = 0;
	}
	
	
	private int findNumOfLinesToHitcircle(List<String> lines) throws IOException  {
			int count = 0;
			boolean flagFound = false;
			for(String line: lines) {
				//System.out.println(line);
				count += 1;
				if(line.contains("[HitObjects]")){
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
