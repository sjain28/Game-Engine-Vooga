package authoring.gui.animation;

import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Shape;

/**
 * A subclass of animation path, used to determine the control points of cubic curves. In this case, the control points
 * are the start, end, and two control points defined for the Bezier curve.
 * 
 * @author Aditya Srinivasan
 *
 */
public class CubicCurvePath extends AnimationPath {

	public CubicCurvePath(Shape shape) {
		super(shape);
	}

	/**
	 * Returns the relevant X controls for a Cubic Curve.
	 */
	@Override
	public Double[] getXControls() {
		return new Double[] {((CubicCurve) myShape).getStartX(), ((CubicCurve) myShape).getControlX1(), ((CubicCurve) myShape).getControlX2(), ((CubicCurve) myShape).getEndX()};
	}

	/**
	 * Returns the relevant Y controls for a Cubic Curve.
	 */
	@Override
	public Double[] getYControls() {
		return new Double[] {((CubicCurve) myShape).getStartY(), ((CubicCurve) myShape).getControlY1(), ((CubicCurve) myShape).getControlY2(), ((CubicCurve) myShape).getEndY()};
	}

}
