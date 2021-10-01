package it.unibo.osu.view;

import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class NeonButton {
	private Button button;

	public Button getButton() {
		return this.button;
	}

	public NeonButton(final String text) {
		try {
			this.button = FXMLLoader.load(this.getClass().getResource("/view/NeonPinkButton.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.button.setText(text);

		this.button.setOnMouseEntered(e -> {
			this.button.setStyle("-fx-background-color: pink; -fx-background-radius: 50;");
		});

		this.button.setOnMouseExited(e -> {
			this.button.setStyle("-fx-background-color: white; -fx-background-radius: 50;");
		});

	}
}