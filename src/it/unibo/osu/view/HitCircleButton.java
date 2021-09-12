package it.unibo.osu.view;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HitCircleButton {
	private Button button;
	private Circle circle;
	private Group group;
	private Circle ring;
	private Text text;
	private final static double RADIUS = 75.0;

	public HitCircleButton(final int number) {
		this.button = new Button(String.valueOf(number));
		this.circle = new Circle(RADIUS);
		this.circle.setFill(Color.RED);
		this.circle.setStroke(Color.WHITESMOKE);
		this.circle.setStrokeWidth(5);
		this.ring = new Circle(1.25 * RADIUS, Color.TRANSPARENT);
		this.ring.setCenterX(this.circle.getCenterX());
		this.ring.setCenterY(this.circle.getCenterY());
		this.ring.setStroke(Color.WHITESMOKE);
		this.text = new Text(String.valueOf(number));
		this.text.setFont(new Font(30));
		this.text.setFill(Color.WHITESMOKE);
		this.text.setX(this.circle.getCenterX());
		this.text.setY(this.circle.getCenterY());

		this.group = new Group(this.circle, this.ring, this.text);

		ScaleTransition trans = new ScaleTransition(Duration.seconds(0.125), this.group);
		trans.setByX(0.2);
		trans.setByY(0.2);

		this.group.setOnMouseClicked(e -> {
			trans.play();
			trans.setOnFinished(end -> {
				this.group.setVisible(false);
			});
		});

	}

	public Group getGroup() {
		return this.group;
	}
}
