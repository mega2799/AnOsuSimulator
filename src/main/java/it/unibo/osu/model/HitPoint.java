package it.unibo.osu.model;

import javafx.geometry.Point2D;

public interface HitPoint {

	/**
	 * Sets the {@link Point2D}.
	 *
	 * @param position the new position
	 */
	void setPosition(Point2D position);

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	void setTime(double time);

	/**
	 * Gets the {@link Point2D}.
	 *
	 * @return the position
	 */
	Point2D getPosition();

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	double getTime();

	/**
	 * Gets the x axis position.
	 *
	 * @return the x
	 */
	double getX();

	/**
	 * Gets the y axis position.
	 *
	 * @return the y
	 */
	double getY();

	/**
	 * Sets the x axis position.
	 *
	 * @param x the new x
	 */
	void setX(double x);

	/**
	 * Sets the y axis position.
	 *
	 * @param y the new y
	 */
	void setY(double y);

}