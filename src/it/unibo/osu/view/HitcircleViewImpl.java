package it.unibo.osu.view;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HitcircleViewImpl implements HitcircleView {
	private ImageView innerCircle;
	private ImageView outerCircle;
	private double circleSize;
	private double overallDifficulty; //<--
	private double approachRate;
	private ScaleTransition scaleOuterCircle;
	private FadeTransition fadeInnerCircle;
	private FadeTransition fadeOuterCircle;
	private ParallelTransition pararallelTrans;
	private double x;
	private double y;
	private double fadeInTime;
	private double approachTime;
	
	
	public HitcircleViewImpl(String innerCircleUrl, String outerCircleUrl, double circleSize, double overallDifficulty,
			double approachRate,double x, double y) {
		this.innerCircle = new ImageView(new Image(this.getClass().getResource(innerCircleUrl).toString()));
		this.outerCircle = new ImageView(new Image(this.getClass().getResource(outerCircleUrl).toString()));
		this.circleSize = circleSize;
		this.overallDifficulty = overallDifficulty;
		this.approachRate = approachRate;
		this.scaleOuterCircle = new ScaleTransition();
		
		this.fadeInnerCircle = new FadeTransition();
		this.fadeOuterCircle = new FadeTransition();
		this.pararallelTrans = new ParallelTransition(this.fadeInnerCircle,this.fadeOuterCircle);
		this.x = x;
		this.y = y;
		this.fadeInTime = this.getFadeInTime();
		this.approachTime = this.getApproachTime();
		this.init();
		
	}

	
	private void init() {
		double innerCircleSize = 109 - 9 * this.circleSize;
		double outerCircleSize = innerCircleSize * 2;
		this.innerCircle.setFitHeight(innerCircleSize);
		this.innerCircle.setFitWidth(innerCircleSize);
		this.outerCircle.setFitHeight(outerCircleSize);
		this.outerCircle.setFitWidth(outerCircleSize);	
		
		this.innerCircle.setLayoutX(this.x - this.innerCircle.getFitWidth() / 2);
		this.innerCircle.setLayoutY(this.y - this.innerCircle.getFitHeight() / 2);
		this.outerCircle.setLayoutX(this.x - this.outerCircle.getFitWidth() / 2);
		this.outerCircle.setLayoutY(this.y - this.outerCircle.getFitHeight() / 2);
		
		this.scaleOuterCircle.setNode(this.outerCircle);
		this.scaleOuterCircle.setToX(0.6);
		this.scaleOuterCircle.setToY(0.6);
		this.scaleOuterCircle.setCycleCount(1);
		this.scaleOuterCircle.setDuration(Duration.millis(this.approachTime));
		
		this.fadeInnerCircle.setNode(this.innerCircle);
		this.fadeInnerCircle.setCycleCount(1);
		this.fadeInnerCircle.setFromValue(0);
		this.fadeInnerCircle.setToValue(1);
		this.fadeInnerCircle.setDuration(Duration.millis(this.fadeInTime));
		
		this.fadeOuterCircle.setNode(this.outerCircle);
		this.fadeOuterCircle.setCycleCount(1);
		this.fadeOuterCircle.setFromValue(0);
		this.fadeOuterCircle.setToValue(1);
		this.fadeOuterCircle.setDuration(Duration.millis(this.fadeInTime));
		this.pararallelTrans.setOnFinished(e -> this.scaleOuterCircle.play());
		
	}

	@Override
	public List<ImageView> getChildren() {
		List<ImageView> list = new ArrayList<>();
		list.add(this.innerCircle);
		list.add(this.outerCircle);
		return list;
	}

	@Override
	public ParallelTransition getParallelTransition() {
		return this.pararallelTrans;
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
		return preempt - this.getFadeInTime();
	}
}
