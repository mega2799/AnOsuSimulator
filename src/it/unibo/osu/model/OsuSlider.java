package it.unibo.osu.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class OsuSlider {
	private Point2D head;

	private Point2D tail;

	private Line body;

	public OsuSlider(final Point2D head, final Point2D tail) {
		super();
		this.head = head;
		this.tail = tail;
		this.body = new Line(head.getX(), head.getY(), tail.getX(), tail.getY());
	}

	public final Point2D getHead() {
		return head;
	}

	public final void setHead(Point2D head) {
		this.head = head;
	}

	public final Point2D getTail() {
		return tail;
	}

	public final void setTail(Point2D tail) {
		this.tail = tail;
	}

	public final Line getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "Slider [head=" + head + ", tail=" + tail + ", body=" + body + "]";
	}

}
