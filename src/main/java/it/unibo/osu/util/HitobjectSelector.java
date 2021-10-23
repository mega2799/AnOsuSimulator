package it.unibo.osu.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unibo.osu.model.HitpointImpl;

/**
 * The Class HitobjectSelector.
 */
public class HitobjectSelector {

    private double time;

    private List<HitpointImpl> hitobjects;

    /**
     * Instantiates a new hitobject selector.
     *
     * @param hitobjects
     */
    public HitobjectSelector(final List<HitpointImpl> hitobjects) {
        this.hitobjects = new ArrayList<>(hitobjects);
    }

    /**
     * Next hitobjects.
     *
     * @param t the t
     * @return the list
     */
    public List<HitpointImpl> nextHitobjects(final double t) {
        if (!this.hasElements()) {
            return List.of();
        }
        this.time += t;
        List<HitpointImpl> hitobjectsToReturn = new ArrayList<>();
        Iterator<HitpointImpl> iterator = this.hitobjects.iterator();
        while (iterator.hasNext()) {
            HitpointImpl next = iterator.next();
            if (next.getTime() <= this.time) {
                hitobjectsToReturn.add(next);
            }
        }
        this.hitobjects.removeAll(hitobjectsToReturn);
        return hitobjectsToReturn;
    }

    /**
     * Checks for elements.
     *
     * @return true, if successful
     */
    public boolean hasElements() {
        return !this.hitobjects.isEmpty();
    }

}
