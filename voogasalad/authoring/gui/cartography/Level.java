package authoring.gui.cartography;

import authoring.CustomText;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Level extends VBox {
	
	private Circle circle;
	private String name;

	public Level(String levelName, double size) {
		this.name = levelName;
		circle = new Circle(size, Paint.valueOf(new RandomColor().getRandomColor()));
		
		this.getChildren().addAll(circle, new CustomText(levelName));
		this.setAlignment(Pos.CENTER);
	}
	
	public String getName() {
		return this.name;
	}
	
}
