

import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.osu.model.BeatMapImpl;
import it.unibo.osu.model.HitpointImpl;
import it.unibo.osu.util.BeatmapReader;
import it.unibo.osu.util.HitobjectSelector;

public class TestHitobjectSelector {

	@Test
	public void getHipointsTest() {
		HitobjectSelector selector = new HitobjectSelector(new BeatMapImpl("/beatmaps/legendsNeverDie.osu").getHitpoints());
		while(selector.hasElements()) {
			List<HitpointImpl> elements = selector.nextHitobjects(020);
			if(!elements.equals(List.of())) {
				System.out.println(elements);
			}
		}
	}

	@Test
	public void getStartingTimeTest() {
		try(BeatmapReader reader = new BeatmapReader(this.getClass().getResourceAsStream("/beatmaps/legendsNeverDie.osu"))){
			System.out.println(reader.getStartingTime());
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void getBreakTimesTest() {
		try(BeatmapReader reader = new BeatmapReader(this.getClass().getResourceAsStream("/beatmaps/legendsNeverDie.osu"))){
			System.out.println(reader.getBreakTimes());
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
	}
}
