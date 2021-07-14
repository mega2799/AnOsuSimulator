package it.unibo.osu.View;


import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MenuButton extends StackPane{
	private Text text;
	private static final int FONT_SIZE = 30;
	
	public MenuButton(final String name, final double buttonWeight, final double buttonHeight) {
		this.text = new Text(name);		
		text.setFont(text.getFont().font(FONT_SIZE));
		text.setFill(Color.WHITE);
		
		Rectangle re = new Rectangle(buttonWeight, buttonHeight);
		re.setOpacity(.8);
		re.setFill(Color.BLACK);
		re.setEffect(new GaussianBlur(3.5));
		
		setOnMouseEntered(e -> {
			re.setTranslateY(-5);
			text.setTranslateY(-5);
			re.setTranslateX(9);
			text.setTranslateX(9);
			re.setTranslateZ(40);
			re.setFill(Color.WHITE);
			text.setFill(Color.BLACK);
		});
		
		setOnMouseExited(e -> {
			re.setTranslateY(0);
			text.setTranslateY(0);
			re.setTranslateX(0);
			text.setTranslateX(0);
			re.setTranslateZ(0);
			re.setFill(Color.BLACK);
			text.setFill(Color.WHITE);
		});
		
		setAlignment(Pos.CENTER);
		setRotate(-0.5);
		getChildren().addAll(re, text);
		
	}
}
