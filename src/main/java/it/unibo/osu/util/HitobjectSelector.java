package it.unibo.osu.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unibo.osu.model.HitpointImpl;

public class HitobjectSelector {
	private double time;
	private List<HitpointImpl> hitobjects;
	
	public HitobjectSelector(List<HitpointImpl> hitobjects) {
		this.hitobjects = new ArrayList<>(hitobjects);
	}
	
	public List<HitpointImpl> nextHitobjects(double t){
		if(!this.hasElements()) {
			return List.of();
		}
		this.time += t;
		List<HitpointImpl> hitobjectsToReturn = new ArrayList<>();
		Iterator<HitpointImpl> iterator = this.hitobjects.iterator();
		while(iterator.hasNext()) {
			HitpointImpl next = iterator.next();
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
