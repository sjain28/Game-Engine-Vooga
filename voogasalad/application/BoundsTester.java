package application;

import java.util.Vector;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tools.Pair;

public class BoundsTester extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane pane = new StackPane();
		Scene scene = new Scene(pane, 800, 800);
		Rectangle rect1 = new Rectangle(200, 200);
		//Rectangle rect2 = new Rectangle(200, 200);
		rect1.setFill(Paint.valueOf("red"));
		//rect2.setFill(Paint.valueOf("blue"));
		rect1.setRotate(45);
		//rect2.setRotate(45);
		//rect2.setTranslateX(-200/Math.sqrt(2));
		//rect2.setTranslateY(200/Math.sqrt(2) + 30);
		pane.getChildren().addAll(rect1);//, rect2);
		//if(rect1.getBoundsInParent().intersects(rect2.getBoundsInParent())) {
			System.out.println("COLLISION");
			determineAngle(rect1, null);//, rect2);
		//}
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void determineAngle(Rectangle r1, Rectangle r2) {
		double rotation = r1.getRotate();
		r1.setRotate(0);
		Bounds normalBounds = r1.getBoundsInParent();
		r1.setRotate(rotation);
		
		Vector<Pair<Double, Double>> top = new Vector<Pair<Double, Double>>();
		Vector<Pair<Double, Double>> bottom = new Vector<Pair<Double, Double>>();
		Vector<Pair<Double, Double>> left = new Vector<Pair<Double, Double>>();
		Vector<Pair<Double, Double>> right = new Vector<Pair<Double, Double>>();
		
		double diagDist = Math.sqrt(Math.pow(r1.getWidth(), 2) + Math.pow(r1.getHeight(), 2));
		double radius = diagDist/2;
		
		System.out.println("x_old " + normalBounds.getMinX());
		System.out.println("x_new " + (normalBounds.getMinX() + radius*Math.sin(Math.toRadians(rotation))));
		
		System.out.println("y_old " + normalBounds.getMinX());
		System.out.println("y_new " + (normalBounds.getMinX() - radius*(1 - Math.cos(Math.toRadians(rotation)))));
	
	}

}
