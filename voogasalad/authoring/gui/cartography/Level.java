package authoring.gui.cartography;

import authoring.CustomText;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Class to represent levels in the level map.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */
public class Level extends VBox {

	/**
	 * private instance variables
	 */
	private transient Circle circle;
	private String name;

	/**
	 * Creates a level in the cartography map with a level name and size.
	 * @param levelName
	 * @param size
	 */
	public Level(String levelName, double size) {
		this.name = levelName;
		circle = new Circle(size, Paint.valueOf(new RandomColor().getRandomColor()));

		this.getChildren().addAll(circle, new CustomText(levelName));
		this.setAlignment(Pos.CENTER);
	}

	/**
	 * Returns the name of the level.
	 * @return
	 */
	public String getName() {
		return this.name;
	}

}
