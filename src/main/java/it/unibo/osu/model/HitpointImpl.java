package it.unibo.osu.model;


import javafx.geometry.Point2D;

public class HitpointImpl implements HitPoint {
	private Point2D position;
	private double time;
	
	public HitpointImpl(Point2D position, double time) {
		this.position = position;
		this.time = time;
	}
	public HitpointImpl(double x,double y, double time) {
		this.position = new Point2D(x, y);
		this.time = time;
	}
	@Override
	public void setPosition(Point2D position) {
		this.position = position;
	}
	@Override
	public void setTime(double time) {
		this.time = time;
	}
	@Override
	public Point2D getPosition() {
		return position;
	}
	@Override
	public double getTime() {
		return time;
	}
	@Override
	public double getX() {
		return this.position.getX();
	}
	@Override
	public double getY() {
		return this.position.getY();
	}
	@Override
	public void setX(double x) {
		this.position = new Point2D(x, this.position.getY());
	}
	@Override
	public void  setY(double y) {
		this.position = new Point2D(this.position.getX(), y);
	}
	@Override
	public String toString() {
		return "[" + this.position + ", time " + this.time +"]";
	}
}
