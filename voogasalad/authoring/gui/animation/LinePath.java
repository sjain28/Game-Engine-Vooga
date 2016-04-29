package authoring.gui.animation;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 * A subclass of the AnimationPath that is used to provide the specific
 * control points for a line.
 * 
 * @author Aditya Srinivasan
 *
 */
public class LinePath extends AnimationPath {

	/**
	 * Initializes the line path.
	 * @param shape
	 */
	public LinePath(Shape shape) {
		super(shape);
	}

	/**
	 * Returns the X control points for the line.
	 */
	@Override
	public Double[] getXControls() {
		return new Double[] {((Line) myShape).getStartX(), ((Line) myShape).getStartX(), ((Line) myShape).getEndX(), ((Line) myShape).getEndX()};
	}

	/**
	 * Returns the Y control points for the line.
	 */
	@Override
	public Double[] getYControls() {
		return new Double[] {((Line) myShape).getStartY(), ((Line) myShape).getStartY(), ((Line) myShape).getEndY(), ((Line) myShape).getEndY()};
	}

}
