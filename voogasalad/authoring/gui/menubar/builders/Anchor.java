package authoring.gui.menubar.builders;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Anchor extends Circle {
	public Anchor(Color color, DoubleProperty x, DoubleProperty y) {
		super(x.get(), y.get(), 10);
		setFill(color.deriveColor(1, 1, 1, 0.5));
		setStroke(color);
		setStrokeWidth(2);
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
