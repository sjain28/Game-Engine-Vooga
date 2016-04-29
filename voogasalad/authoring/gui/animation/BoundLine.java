package authoring.gui.animation;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 * A line defined by startpoints and endpoints linked to properties, allowing it to dynamically "stick" to whichever
 * object it is bound.
 * 
 * @author Aditya Srinivasan
 *
 */

class BoundLine extends Line {
	
	/**
	 * Constants
	 */
	private static final double STROKE_WIDTH = 2;
	private static final double OPACITY = 0.5;
	private static final double DASH1 = 10;
	private static final double DASH2 = 5;
	
	/**
	 * Initializes the line and its starting and ending coordinates.
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
		startXProperty().bind(startX);
		startYProperty().bind(startY);
		endXProperty().bind(endX);
		endYProperty().bind(endY);
		setStrokeWidth(STROKE_WIDTH);
		setStroke(Color.GRAY.deriveColor(0, 1, 1, OPACITY));
		setStrokeLineCap(StrokeLineCap.BUTT);
		getStrokeDashArray().setAll(DASH1, DASH2);
	}
}