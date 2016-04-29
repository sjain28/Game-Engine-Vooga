package authoring.gui.animation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * A GUI container for an image, to be used when viewing image animation sequences.
 * 
 * @author Aditya Srinivasan
 *
 */
public class Frame extends StackPane {
	
	/**
	 * Constants
	 */
	private static final double SIZE = 75;
	private static final String TRANSPARENT = "transparent";
	private static final String BORDER_COLOR = "white";
	private static final double WIDTH = 3;
	private static final String PREFIX = "file:";
	
	private String path;
	
	/**
	 * Initializes the rectangle frame and places the image inside of it as specified by the image path.
	 * @param path
	 */
	public Frame(String path) {
		this.path = path;
		Rectangle frame = new Rectangle(SIZE, SIZE, Color.WHITE);
		frame.setFill(Paint.valueOf(TRANSPARENT));
		frame.setStroke(Paint.valueOf(BORDER_COLOR));
		frame.setStrokeWidth(WIDTH);
		Image image = new Image(PREFIX + path, SIZE, SIZE, false, false);
		this.getChildren().addAll(new ImageView(image), frame);
	}
	
	/**
	 * Returns the path of the image contained by the frame.
	 * @return
	 */
	public String getImagePath() {
		return path;
	}

}
