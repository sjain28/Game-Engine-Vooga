package authoring.gui.animation;

import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Shape;

public class CubicCurvePath extends AnimationPath {

	public CubicCurvePath(Shape shape) {
		super(shape);
	}

	@Override
	public double[] getXControls() {
		return new double[] {((CubicCurve) myShape).getStartX(), ((CubicCurve) myShape).getControlX1(), ((CubicCurve) myShape).getControlX2(), ((CubicCurve) myShape).getEndX()};
	}

	@Override
	public double[] getYControls() {
		return new double[] {((CubicCurve) myShape).getStartY(), ((CubicCurve) myShape).getControlY1(), ((CubicCurve) myShape).getControlY2(), ((CubicCurve) myShape).getEndY()};
	}

}
