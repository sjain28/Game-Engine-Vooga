package authoring.gui.cartography;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Anchor extends Circle {

	private static final double RADIUS = 10;
	private static final double CIRCLE_WIDTH = 2;
	private static final double COLOR_FACTOR = 0.5;
	
	public Anchor(DoubleProperty x, DoubleProperty y, TransitionOrder order) {
		super(x.get(), y.get(), RADIUS);
		setFill(order.getColor().deriveColor(1, 1, 1, COLOR_FACTOR));
		setStroke(order.getColor());
		setStrokeWidth(CIRCLE_WIDTH);
		setStrokeType(StrokeType.OUTSIDE);

		x.bind(centerXProperty());
		y.bind(centerYProperty());
	}

}