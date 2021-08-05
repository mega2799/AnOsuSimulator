package it.unibo.osu.View;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Transform;

public class MenuSkinButton {
	private static final double HEIGHT = 150;
	private static final double WIDTH = 500;
	private Button btn;

	public MenuSkinButton(final String pathToFile) {
		Image imm = new Image(this.getClass().getResource(pathToFile).toString());
		ImageView skin = new ImageView(imm);
		skin.setFitHeight(HEIGHT);
		skin.setFitWidth(WIDTH);
		this.btn = new Button(null, skin);
		this.btn.setStyle(
				"-fx-background-radius: 5em;" + 
				"-fx-min-width: 3px; " +
                "-fx-min-height: 3px; " +
                "-fx-max-width: 3px; " +
                "-fx-max-height: 3px;");
		
		this.btn.setOnMouseEntered(e -> {
			this.btn.getTransforms().setAll(Transform.translate(50, 0));
		});
		
		this.btn.setOnMouseExited(e -> {
			this.btn.getTransforms().setAll(Transform.translate(-50, 0));
		});
	}
	
	public Button getSkinnedButton() {
		return this.btn; 
	}
}
