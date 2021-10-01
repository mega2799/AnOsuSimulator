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

public class Clock implements HitActionObserver{
	private Text text;
	Timeline timeline;
	int mins = 0, secs = 0, millis = 0;
	boolean sos = true;
	private Text endTime;
	private Map<String, GamePoints> timeStatistic = new HashMap<>();

	void change(Text text) {
		if(millis == 1000) {
			secs++;
			millis = 0;
		}
		if(secs == 60) {
			mins++;
			secs = 0;
		}
		text.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
		 + (((secs/10) == 0) ? "0" : "") + secs + ":" 
			+ (((millis/10) == 0) ? "00" : (((millis/100) == 0) ? "0" : "")) + millis++);
    }

	public void start() {
		text = new Text("00:00:000");
		timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
            	change(text);
			}
		}));

		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);
            	if(sos) {
            		timeline.play();
            		sos = false;
            	} else {
            		timeline.pause();
            		sos = true;
            	}
	}
	
	public String getCurrent() {
		return this.text.toString();
	}
	
	public void stop() {
		timeline.stop();
		this.endTime = this.text;
	}

	@Override
	public void onNotify(GamePoints points) {
		this.timeStatistic.put(this.text.getText(), points);
		System.out.println(points);
	}
	
}