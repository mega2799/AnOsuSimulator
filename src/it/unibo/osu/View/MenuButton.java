package it.unibo.osu.View;


import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MenuButton extends StackPane{
	private Text text;
	
	public MenuButton(final String name) {
		this.text = new Text(name);		
		text.setFont(text.getFont().font(30));
		text.setFill(Color.WHITE);
		
		Rectangle re = new Rectangle(160, 40);
		re.setOpacity(.8);
		re.setFill(Color.BLACK);
		re.setEffect(new GaussianBlur(3.5));
		
		setAlignment(Pos.CENTER);
		setRotate(-0.5);
		getChildren().addAll(re, text);
	}
}
