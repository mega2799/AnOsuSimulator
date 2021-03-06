package it.unibo.osu.util;

import java.util.HashMap;
import java.util.Map;

import it.unibo.osu.controller.HitActionObserver;
import it.unibo.osu.model.GamePoints;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * The Class Clock.
 */
public class Clock implements HitActionObserver {

    private Text text;
    private Timeline timeline;
    private int mins = 0;
    private int secs = 0;
    private int millis = 0;
    private boolean sos = true;

    /** The end time. */
    private Text endTime;

    /** The time statistic. */
    private Map<String, GamePoints> timeStatistic = new HashMap<>();

    void change(final Text text) {
        if (millis == 1000) {
            secs++;
            millis = 0;
        }
        if (secs == 60) {
            mins++;
            secs = 0;
        }
        text.setText(
                (((mins / 10) == 0) ? "0" : "") + mins + ":"
                        + (((secs / 10) == 0) ? "0" : "") + secs + ":"
                        + (((millis / 10) == 0) ? "00"
                                : (((millis / 100) == 0) ? "0" : ""))
                        + millis++);
    }

    /**
     * Start.
     */
    public void start() {
        text = new Text("00:00:000");
        timeline = new Timeline(new KeyFrame(Duration.millis(1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        change(text);
                    }
                }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        if (sos) {
            timeline.play();
            sos = false;
        } else {
            timeline.pause();
            sos = true;
        }
    }

    /**
     * Gets the time statistic.
     *
     * @return the time statistic
     */
    public final Map<String, GamePoints> getTimeStatistic() {
        return timeStatistic;
    }

    /**
     * Gets the current.
     *
     * @return the current
     */
    public String getCurrent() {
        return this.text.toString();
    }

    /**
     * Pause.
     */
    public void pause() {
        timeline.pause();
    }

    /**
     * Stop.
     */
    public void stop() {
        timeline.stop();
        this.endTime = this.text;
    }

    /**
     * On notify.
     *
     * @param points the points
     */
    @Override
    public void onNotify(final GamePoints points) {
        this.timeStatistic.put(this.text.getText(), points);
    }

}