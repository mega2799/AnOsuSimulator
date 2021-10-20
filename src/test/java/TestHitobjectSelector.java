

import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.osu.model.BeatMap;
import it.unibo.osu.model.HitpointImpl;
import it.unibo.osu.util.BeatmapReader;
import it.unibo.osu.util.HitobjectSelector;

public class TestHitobjectSelector {

//	@Test
//	public void getHipointsTest() {
//		HitobjectSelector selector = new HitobjectSelector(new BeatMap("/beatmaps/legendsNeverDie.osu").getHitpoints());
//		while(selector.hasElements()) {
//			List<Hitpoint> elements = selector.nextHitobjects(020);
//			if(!elements.equals(List.of())) {
//				System.out.println(elements);
//			}
//		}
//	}
//	
//	@Test 
//	public void getStartingTimeTest() {
//		try(BeatmapReader reader = new BeatmapReader(new FileReader(new File(this.getClass().getResource("/beatmaps/legendsNeverDie.osu").toURI())))){
//			System.out.println(reader.getStartingTime());
//		} catch (IOException | URISyntaxException e) {
//			fail();
//			e.printStackTrace();	
//		}
//	}
//	
//	@Test
//	public void getBreakTimesTest() {
//		try(BeatmapReader reader = new BeatmapReader(new FileReader(new File(this.getClass().getResource("/beatmaps/legendsNeverDie.osu").toURI())))){
//			System.out.println(reader.getBreakTimes());
//		} catch (IOException | URISyntaxException e) {
//			fail();
//			e.printStackTrace();	
//		}
//	}
}
