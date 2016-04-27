package authoring.gui.animation;

import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Shape;

public class CubicCurvePath extends AnimationPath {

	public CubicCurvePath(Shape shape) {
		super(shape);
	}

	@Override
	public Double[] getXControls() {
		return new Double[] {((CubicCurve) myShape).getStartX(), ((CubicCurve) myShape).getControlX1(), ((CubicCurve) myShape).getControlX2(), ((CubicCurve) myShape).getEndX()};
	}

	@Override
	public Double[] getYControls() {
		return new Double[] {((CubicCurve) myShape).getStartY(), ((CubicCurve) myShape).getControlY1(), ((CubicCurve) myShape).getControlY2(), ((CubicCurve) myShape).getEndY()};
	}

}
