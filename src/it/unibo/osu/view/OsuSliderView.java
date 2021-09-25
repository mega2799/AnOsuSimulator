package it.unibo.osu.view;

import it.unibo.osu.model.OsuSlider;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

public class OsuSliderView {
	
	private Group slider;

	public OsuSliderView(final OsuSlider s) {
		Line ll = s.getBody();
		
		ll.setStroke(Color.WHITE);
		ll.setStrokeType(StrokeType.CENTERED);
		ll.setStrokeWidth(15);
		//ll.getStrokeDashArray().addAll(25d, 10d); utile per poter fare le linee tratteggaite tra i hitcirle 

		ll.setOnMouseEntered(e -> {
			System.out.println("dentro");
		});
		
		ll.setOnMouseExited(e -> {
			System.out.println("fuori come un balcone");
		});

		//this.slider = new Group(ll, new HitCircleButton(s.getHead(), 0).getGroup(),
			//						new HitCircleButton(s.getTail(),0).getGroup());
	}

	public final Group getSlider() {
		return slider;
	}
	
}
