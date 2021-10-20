package it.unibo.osu.model;

import javafx.geometry.Point2D;

public interface HitPoint {

	void setPosition(Point2D position);

	void setTime(double time);

	Point2D getPosition();

	double getTime();

	double getX();

	double getY();

	void setX(double x);

	void setY(double y);

}