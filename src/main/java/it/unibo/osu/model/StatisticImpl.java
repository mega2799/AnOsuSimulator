package it.unibo.osu.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * The Class StatisticImpl.
 */
public class StatisticImpl implements Statistic {

    private static Statistic statistic;

    private Map<String, List<String>> map = new HashMap<>();

    /**
     * Instantiate a {@link Statistic} object, use of Singleton.
     *
     * @return the stat
     */
    public static final Statistic getStat() {
        if (statistic == null) {
            statistic = new StatisticImpl();
        }
        return statistic;
    }

    @Override
    public List<String> getPlayerHistory(final String player) {
        return this.map.get(player);
    }

    @Override
    public void addResult(final String player, final String points) {
        if (!this.map.containsKey(player)) {
            if (this.map.get(player) == null) {
                this.map.put(player, new ArrayList<String>());
            }
        }
        this.map.get(player).add(points);
    }

    @Override
    public void addPlayer(final String player) {
        if (!this.map.containsKey(player)) {
            this.map.put(player, new ArrayList<String>());
        }
    }

    @Override
    public void writeJson() throws IOException {
        JsonFactory jF = new JsonFactory();
        JsonGenerator jG;
        jG = jF.createGenerator(new File("users.json"), JsonEncoding.UTF8);
        jG.useDefaultPrettyPrinter();
        jG.writeStartObject();

        this.map.forEach((k, v) -> {
            try {
                jG.writeStringField(k, v.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jG.close();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void readJson() throws JsonParseException, IOException {
        JsonFactory jF = new JsonFactory();
        File f = new File("users.json");
        JsonParser jP = jF.createJsonParser(f);
        /*
         * Sanity check: verify that we got "Json Object"
         */
        if (jP.nextToken() != JsonToken.START_OBJECT) {
            throw new IOException("Expected data to start with an Object");
        }
        while (jP.nextToken() != JsonToken.END_OBJECT) {
            String player = jP.getCurrentName();
            String pointList = jP.getValueAsString();

            List<String> l = new ArrayList<String>(Arrays.asList(pointList
                    .substring(1, pointList.length() - 1).split(", ")));
            this.map.put(player, l);
        }
    }

}
