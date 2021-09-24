package it.unibo.osu.view;

import it.unibo.osu.model.OsuSpinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// per non so quale motivo non riesce ad apparire sulla scene


public class OsuSpinnerView {
	private OsuSpinner spinToWin;
	
	private ImageView skin;

    private double mousePosX, mousePosY = 0;

	public OsuSpinnerView(final double duration) {
		this.spinToWin = new OsuSpinner(duration);
		setSkin();
	}


	private void setSkin() {
		Image imm = new Image(this.getClass().getResource("/image/spinner.png").toString());
		this.skin = new ImageView(imm);
		
		this.skin.setFitWidth(1000);
		this.skin.setFitHeight(800);
		// handle mouse events 
		this.skin.setOnMousePressed(e -> {
            mousePosX = e.getSceneX();
            mousePosY = e.getSceneY();
            
            System.out.println("" + mousePosX + "" + mousePosY);
        });
	}	

	public ImageView getSpinner() {
		return this.skin;
	}
		
}
