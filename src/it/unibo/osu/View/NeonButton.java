package it.unibo.osu.View;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class NeonButton extends Button{

	private Button neon;

	public final Button getNeon() {
		return this.neon;
	}

	public NeonButton(final String text) throws IOException {

		this.neon = FXMLLoader.load(ClassLoader.getSystemResource("fxmlStuff/NeonButton.fxml"));
		
		this.neon.setText(text);
		
		this.neon.setOnMouseClicked(e -> {
			System.out.println(text);
		});
		/*
		this.neon.setOnMouseEntered(e -> {
			this.neon.setStyle("text-fill: RED");
		});
		
		this.neon.setOnMouseExited(e -> {
			this.neon.setStyle("text-fill: WHITE");
		});
		*/
	}

}
