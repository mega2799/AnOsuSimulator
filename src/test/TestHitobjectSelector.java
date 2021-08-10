package test;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

import it.unibo.osu.model.BeatMap;
import it.unibo.osu.model.Hitpoint;
import it.unibo.osu.util.BeatmapReader;
import it.unibo.osu.util.HitobjectSelector;

public class TestHitobjectSelector {

	@Test
	public void test() {
		HitobjectSelector selector = new HitobjectSelector(new BeatMap("/beatmaps/legendsNeverDie.osu").getHitpoints());
		while(selector.hasElements()) {
			List<Hitpoint> elements = selector.nextHitobjects(020);
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
	
	@Test 
	public void tets2() {
		try(BeatmapReader reader = new BeatmapReader(new FileReader(new File(this.getClass().getResource("/beatmaps/legendsNeverDie.osu").toURI())))){
			System.out.println(reader.getStartingTime());
		} catch (IOException | URISyntaxException e) {
			fail();
			e.printStackTrace();	
		}
	}
	
	@Test
	public void test3() {
		try(BeatmapReader reader = new BeatmapReader(new FileReader(new File(this.getClass().getResource("/beatmaps/legendsNeverDie.osu").toURI())))){
			System.out.println(reader.getBreakTimes());
		} catch (IOException | URISyntaxException e) {
			fail();
			e.printStackTrace();	
		}
	}
}
