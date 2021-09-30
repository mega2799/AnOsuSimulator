package it.unibo.osu.view;

import java.util.ArrayList;
import java.util.List;

import it.unibo.osu.controller.HitActionObserver;
import it.unibo.osu.controller.HitActionSubject;
import it.unibo.osu.model.GamePoints;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HitCircleButton  implements HitcircleView, HitActionSubject {
	private double radius;
	private Button button;
	private Circle circle;
	private Group group;
	private Circle ring;
	private Text text;
	
	//qui ci andrebbe solo la parte della view, il resto va preso dal model....
	private double overallDifficulty; //<--
	private double approachRate;
	
	private double fadeTime;
	private double approachTime;

	private List<HitActionObserver> observers = new ArrayList<>();

	private ScaleTransition scaleRing;

	private FadeTransition groupFade;

	private ParallelTransition pararallelTrans;

	public final double getRadius() {
		return this.radius;
	}

	public HitCircleButton(final Point2D head,final double radius, final int number, final double overallDifficulty, 
			final double approachRate) {

		this.fadeTime = getFadeInTime();
		this.approachTime = getApproachTime() * 1.3;
		
		this.overallDifficulty = overallDifficulty;
		computeRadius(radius);
		//this.button = new Button(String.valueOf(number));

		this.circle = new Circle(head.getX(), head.getY(), this.radius);
		this.circle.setFill(Color.RED);
		this.circle.setStroke(Color.WHITESMOKE);
		this.circle.setStrokeWidth(5);

		this.ring = new Circle(2 * this.radius, Color.TRANSPARENT);
		this.ring.setStrokeWidth(7);
		this.ring.setCenterX(this.circle.getCenterX());
		this.ring.setCenterY(this.circle.getCenterY());
		this.ring.setStroke(Color.WHITESMOKE); 
		computeRingScaleTransition();

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
				this.group.setVisible(false); // ho lasciato la mia fadeout in cui si zoomma come su osu
				double time = this.scaleRing.getTotalDuration().toMillis() - this.scaleRing.getCurrentTime().toMillis();
				this.scaleRing.stop();
				GamePoints points = this.getHitWindowScore(time);
				this.notifyObs(points);
			});
		});

	this.scaleRing.setOnFinished(e -> {
			this.group.setVisible(false);
			this.notifyObs(GamePoints.MISS);
		});	
	
	}

	private void computeRingScaleTransition() {
		this.scaleRing = new ScaleTransition();
		this.scaleRing.setNode(this.ring);
		this.scaleRing.setToX(0.5);
		this.scaleRing.setToY(0.5);
		this.scaleRing.setCycleCount(1);
		this.scaleRing.setDuration(Duration.millis(this.approachTime));
		this.scaleRing.play(); 
	}
	
	
	private double getFadeInTime() {
		if(this.approachRate < 5) {
			return 800 + 400 * (5 - this.approachRate) / 5;
		} else if ( this.approachRate == 5) {
			return 800;
		} else {
			return 800 - 500 * (this.approachRate - 5) / 5;
		}
	}
	
	private double getApproachTime() {
		double preempt;
		if( this.approachRate < 5) {
			preempt = 1200 + 600 * (5 - this.approachRate) / 5;
		} else if(this.approachRate == 5) {
			preempt = 1200;
		} else {
			preempt = 1200 - 750 * (this.approachRate - 5) / 5;
		}
		return preempt - this.fadeTime;
	}
	private void computeRadius(double radius) {
		this.radius = (109 - 9 * radius)/2;
	}
@Override
	public void notifyObs(GamePoints points) {
		this.observers.forEach(x -> x.onNotify(points));
	}

	@Override
	public void addObserver(HitActionObserver obs) {
		this.observers.add(obs);
	}

	@Override
	public void removeObserver(HitActionObserver obs) {
		this.observers.remove(obs);
	}

	private GamePoints getHitWindowScore(double time) {
		if(time <= 160 - 12 *  this.overallDifficulty) {
			return GamePoints.PERFECT;
		} else if(time <= 280 - 16 * this.overallDifficulty) {
			return GamePoints.GREAT;
		} else if(time <= 400 - 20 * this.overallDifficulty) {
			return GamePoints.OK;
		} else {
			return GamePoints.MISS;
		}
	}
	
	public Group getGroup() {
		return this.group;
	}

	@Override
	public ParallelTransition getParallelTransition() {
		return this.pararallelTrans;
	}

	@Override
	public List<ImageView> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInputHandlers() {
		// TODO Auto-generated method stub
		
	}

}
