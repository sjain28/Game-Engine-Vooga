package authoring.gui.animation;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LinePath extends AnimationPath {

	public LinePath(Shape shape) {
		super(shape);
	}

	@Override
	public Double[] getXControls() {
		return new Double[] {((Line) myShape).getStartX(), ((Line) myShape).getStartX(), ((Line) myShape).getEndX(), ((Line) myShape).getEndX()};
	}

	@Override
	public Double[] getYControls() {
		return new Double[] {((Line) myShape).getStartY(), ((Line) myShape).getStartY(), ((Line) myShape).getEndY(), ((Line) myShape).getEndY()};
	}

}
