package application;
	



import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane root = new BorderPane();
		Scene scena = new Scene(root,600,600);
		primaryStage.setScene(scena);
		
		Circle circle = new Circle();
		root.getChildren().add(circle);
		circle.setFill(javafx.scene.paint.Color.TRANSPARENT);
		circle.setRadius(50);
		circle.setStrokeWidth(10);
		circle.setStroke(Color.LIGHTPINK);
		circle.setEffect(new DropShadow());
		circle.setCenterX(300);
		circle.setCenterY(300);
		
		ScaleTransition scl = new ScaleTransition();
		scl.setNode(circle);
		scl.setByX(-0.25);
		scl.setByY(-0.25);
		scl.setDuration(Duration.seconds(3));
		primaryStage.show();
		scl.setCycleCount(1);
		
		Circle circ2 = new Circle();
		circ2.setRadius(30);
		circ2.setFill(Color.DEEPPINK);
		circ2.setCenterX(300);
		circ2.setCenterY(300);
		root.getChildren().add(circ2);
		
		
		scl.play();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
