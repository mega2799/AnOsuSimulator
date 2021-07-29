package test;

import java.util.List;

import org.junit.Test;

import it.unibo.osu.Model.BeatMap;
import it.unibo.osu.util.HitobjectSelector;
import it.unibo.osu.util.SpaceTimeCoord;

public class TestHitobjectSelector {

	@Test
	public void test() {
		HitobjectSelector selector = new HitobjectSelector(new BeatMap("/beatmaps/legendsNeverDie.osu").getHitpoints());
		while(selector.hasElements()) {
			List<SpaceTimeCoord> elements = selector.nextHitobjects(020);
			try {
				Thread.sleep(020);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!elements.equals(List.of())) {
				System.out.println(elements);
			}
		}
	}
}
