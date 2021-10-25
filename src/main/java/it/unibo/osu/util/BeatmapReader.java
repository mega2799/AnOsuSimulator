package it.unibo.osu.util;

import it.unibo.osu.model.BeatMap;
import it.unibo.osu.model.HitPoint;
import it.unibo.osu.model.HitpointImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Class BeatmapReader.
 */
public class BeatmapReader extends BufferedReader {

    private List<HitpointImpl> hitpoints;

    private Integer n;

    private int index = 0;

    private List<String> lines;

    /**
     * Instantiates a new {@link BeatMap} reader.
     *
     * @param in the in
     */
    public BeatmapReader(final InputStream in) {
        super(new InputStreamReader(in));
        this.lines = super.lines().collect(Collectors.toList());
        this.setHitpoints();
    }

    /**
     * Sets the hitpoints.
     */
    private void setHitpoints() {
        try {
            this.n = findNumOfLinesToOptions(this.lines,
                    BeatmapOptions.HITOBJECTS);
        } catch (IOException e) {
            System.out.println(
                    "Error: stringa \"[HitObjects]\" non presente nella beatmap!");
            e.printStackTrace();
        }
        this.hitpoints = lines.stream().skip(n.intValue())
                .filter(x -> !x.contains("//")).takeWhile(x -> !x.equals(""))
                .map((x) -> {
                    String[] values = x.split(",");
                    return new HitpointImpl(Double.parseDouble(values[0]),
                            Double.parseDouble(values[1]),
                            Double.parseDouble(values[2]));
                }).collect(Collectors.toList());
    }

    /**
     * Gets the hitpoints.
     *
     * @return the hitpoints
     */
    public List<HitpointImpl> getHitpoints() {
        return this.hitpoints;
    }

    /**
     * Hit points stream.
     *
     * @return the stream
     */
    public Stream<HitpointImpl> hitPointsStream() {
        return this.hitpoints.stream();
    }

    /**
     * Checks for next hitpoint.
     *
     * @return true, if successful
     */
    private boolean hasNextHitpoint() {
        if (this.hitpoints.get(index + 1) != null) {
            return true;
        } else {
            this.resetIndex();
            return false;
        }
    }

    /**
     * Read hitpoint.
     *
     * @return the hit point
     */
    public HitPoint readHitpoint() {
        if (this.hasNextHitpoint()) {
            index += 1;
            return this.hitpoints.get(index);
        } else {
            return null;
        }
    }

    /**
     * Reset index.
     */
    public void resetIndex() {
        this.index = 0;
    }

    /**
     * Find num of lines to options.
     *
     * @param lines the lines
     * @param opt   the options
     * @return the int
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private int findNumOfLinesToOptions(final List<String> lines,
            final BeatmapOptions opt) throws IOException {
        int count = 0;
        boolean flagFound = false;
        for (String line : lines) {
            // System.out.println(line);
            count += 1;
            if (line.contains(opt.getValue())) {
                flagFound = true;
                break;
            }
        }
        if (flagFound) {
            return count;
        } else {
            throw new IOException();
        }
    }

    /**
     * Gets the option map.
     *
     * @param opt the options
     * @return the option map
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public HashMap<String, String> getOptionMap(final BeatmapOptions opt)
            throws IOException {
        this.n = findNumOfLinesToOptions(this.lines, opt);
        HashMap<String, String> map = (HashMap<String, String>) this.lines
                .stream().skip(this.n).filter(x -> !x.contains("//"))
                .takeWhile(x -> !x.equals(""))
                .map(x -> Arrays.asList(x.split(":")))
                // sostituire . con ,?
                .collect(Collectors.toMap(x -> x.get(0), x -> x.get(1)));
        return map;
    }

    /**
     * Gets the bakground.
     *
     * @return the bakground
     */
    public String getBakground() {
        try {
            this.n = findNumOfLinesToOptions(this.lines, BeatmapOptions.EVENTS);
        } catch (IOException e) {
            System.out.println(
                    "Error: stringa \"[Events]\" non presente nella beatmap!");
            e.printStackTrace();
        }
        return this.lines.stream().skip(this.n).filter(x -> !x.contains("//"))
                .takeWhile(x -> !x.equals("")).map((x) -> {
                    String[] values = x.split(",");
                    return Arrays.asList(values).get(2);
                }).collect(Collectors.toList()).get(0).replaceAll("\"", "");
    }

    /**
     * Gets the starting time.
     *
     * @return the starting time
     */
    public Double getStartingTime() {
        try {
            this.n = findNumOfLinesToOptions(this.lines,
                    BeatmapOptions.TIMINGPOINTS);
        } catch (IOException e) {
            System.out.println(
                    "Error: stringa \"[Events]\" non presente nella beatmap!");
            e.printStackTrace();
        }
        Double returnValue = this.lines.stream().skip(this.n).limit(1)
                .map(x -> {
                    return Double
                            .parseDouble(Arrays.asList(x.split(",")).get(0));
                }).reduce((x, y) -> x + y).get();
        if (returnValue == null) {
            System.out.println("Missing starting time.");
            throw new RuntimeException();
        } else {
            return returnValue;
        }
    }

    /**
     * Gets the break times.
     *
     * @return the break times
     */
    public List<List<Double>> getBreakTimes() {
        try {
            this.n = findNumOfLinesToOptions(this.lines, BeatmapOptions.EVENTS);
        } catch (IOException e) {
            System.out.println(
                    "Error: stringa \"[Events]\" non presente nella beatmap!");
            e.printStackTrace();
        }
        return this.lines.stream().skip(this.n).filter(x -> !x.contains("//"))
                .takeWhile(x -> !x.equals(""))
                .filter(x -> x.split(",")[0].equals("2"))
                .map((x) -> {
                    String[] values = x.split(",");
                    return Arrays.asList(values).subList(1, 3).stream()
                            .map(y -> Double.parseDouble(y))
                            .collect(Collectors.toList());
                }).collect(Collectors.toList());
    }
}
