package it.unibo.osu.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HitobjectSelector {
	private double time;
	private List<SpaceTimeCoord> hitobjects;
	
	public HitobjectSelector(List<SpaceTimeCoord> hitobjects) {
		this.hitobjects = hitobjects;
	}
	
	public List<SpaceTimeCoord> nextHitobjects(double t){
		if(!this.hasElements()) {
			return List.of();
		}
		this.time += t;
		List<SpaceTimeCoord> hitobjectsToReturn = new ArrayList<>();
		Iterator<SpaceTimeCoord> iterator = this.hitobjects.iterator();
		while(iterator.hasNext()) {
			SpaceTimeCoord next = iterator.next();
			if(next.getTime() <= this.time) {
				hitobjectsToReturn.add(next);
			}
		}
		this.hitobjects.removeAll(hitobjectsToReturn);
		return hitobjectsToReturn;
	}
	
	public boolean hasElements() {
		return !this.hitobjects.isEmpty();
	}
	
}
