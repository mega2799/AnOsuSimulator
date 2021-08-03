package it.unibo.osu.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.BlurType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class NeonButton extends Button {

	private Text text;
	private static final int FONT_SIZE = 30;

	public NeonButton(final String name, final double buttonWeight, final double buttonHeight) {
		this.text = new Text(name);
		setFont(text.getFont().font(FONT_SIZE));
		setText(name);

		DropShadow mineNeon = new DropShadow(BlurType.THREE_PASS_BOX, Color.CYAN, 0, 0, 0, 0);

		mineNeon.setWidth(75);
		mineNeon.setHeight(75);
		// mineNeon.setSpread(0.8); tamarro assai
		mineNeon.setSpread(0.5);

		setOnMouseEntered(e -> this.setEffect(mineNeon));
		setOnMouseExited(e -> this.setEffect(null));

		setAlignment(Pos.CENTER);
	}

}
