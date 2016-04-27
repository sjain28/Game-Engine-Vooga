package authoring.gui.cartography;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Anchor extends Circle {

	public Anchor(DoubleProperty x, DoubleProperty y, TransitionOrder order) {
		super(x.get(), y.get(), 10);
		setFill(order.getColor().deriveColor(1, 1, 1, 0.5));
		setStroke(order.getColor());
		setStrokeWidth(2);
		setStrokeType(StrokeType.OUTSIDE);

		x.bind(centerXProperty());
		y.bind(centerYProperty());
	}

}