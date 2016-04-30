package authoring.gui.menubar.builders;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Anchor extends Circle {
	
	private static final int RADIUS = 10;
	private static final double FILL_CONST = 0.5;
	private static final int STROKE_WIDTH = 2;
	
	public Anchor(Color color, DoubleProperty x, DoubleProperty y) {
		super(x.get(), y.get(), RADIUS);
		setFill(color.deriveColor(1, 1, 1, FILL_CONST));
		setStroke(color);
		setStrokeWidth(STROKE_WIDTH);
		setStrokeType(StrokeType.OUTSIDE);

		x.bind(centerXProperty());
		y.bind(centerYProperty());
		enableDrag();
	}

	private void enableDrag() {
		final Delta dragDelta = new Delta();
		setOnMousePressed(e -> {
			dragDelta.x = getCenterX() - e.getX();
			dragDelta.y = getCenterY() - e.getY();
			getScene().setCursor(Cursor.MOVE);
		});
		setOnMouseReleased(e -> {
			getScene().setCursor(Cursor.HAND);
		});
		enableMouseDrag(dragDelta);
	}
	
	private void enableMouseDrag(Delta dragDelta) {
		setOnMouseDragged(e -> {
			double newX = e.getX() + dragDelta.x;
			if (newX > 0 && newX < getScene().getWidth()) {
				setCenterX(newX);
			}
			double newY = e.getY() + dragDelta.y;
			if (newY > 0 && newY < getScene().getHeight()) {
				setCenterY(newY);
			}
		});
		setOnMouseEntered(e -> {
			if (!e.isPrimaryButtonDown()) {
				getScene().setCursor(Cursor.HAND);
			}
		});
		setOnMouseExited(e -> {
			if (!e.isPrimaryButtonDown()) {
				getScene().setCursor(Cursor.DEFAULT);
			}
		});
	}

	private class Delta {
		double x, y;
	}
}
