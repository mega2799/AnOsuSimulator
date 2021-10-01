package it.unibo.osu.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unibo.osu.model.Hitpoint;

public class HitobjectSelector {
	private double time;
	private List<Hitpoint> hitobjects;
	
	public HitobjectSelector(List<Hitpoint> hitobjects) {
		this.hitobjects = new ArrayList<>(hitobjects);
	}
	
	public List<Hitpoint> nextHitobjects(double t){
		if(!this.hasElements()) {
			return List.of();
		}
		this.time += t;
		List<Hitpoint> hitobjectsToReturn = new ArrayList<>();
		Iterator<Hitpoint> iterator = this.hitobjects.iterator();
		while(iterator.hasNext()) {
			Hitpoint next = iterator.next();
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
