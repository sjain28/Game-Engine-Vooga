package authoring.gui.menubar.builders;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * Anchor class to mark beginning and end of path. ***For bezier curve use***
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */
public class Anchor extends Circle {
	
	/**
	 * private instance variables
	 */
	private static final int RADIUS = 10;
	private static final double FILL_CONST = 0.5;
	private static final int STROKE_WIDTH = 2;
	
	/**
	 * Instatiation of display of anchor.
	 * @param color
	 * @param x
	 * @param y
	 */
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

	/**
	 * Enable drag
	 */
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
	
	/**
	 * Enabling mouse drag
	 * @param dragDelta
	 */
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

	/**
	 * Delta class to assist with drag and dropping.
	 */
	private class Delta {
		double x, y;
	}
}
