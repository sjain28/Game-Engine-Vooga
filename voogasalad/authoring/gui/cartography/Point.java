package authoring.gui.cartography;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Point extends Circle {

	private static final int STROKE_WIDTH = 3;

	public Point(double r, Color c) {
		super(r, c);
		this.setStroke(Color.WHITE);
		this.setStrokeWidth(STROKE_WIDTH);
		enableDrag(this);
	}

	private void enableDrag(final Circle circle) {
		final Delta dragDelta = new Delta();
		circle.setOnMousePressed(e -> {
			dragDelta.x = circle.getCenterX() - e.getX();
			dragDelta.y = circle.getCenterY() - e.getY();
			circle.getScene().setCursor(Cursor.MOVE);
		});
		circle.setOnMouseReleased(e -> circle.getScene().setCursor(Cursor.HAND));

		circle.setOnMouseDragged(e -> {
			circle.setCenterX(e.getX() + dragDelta.x);
			circle.setCenterY(e.getY() + dragDelta.y);
		});

		enableDragCursor(circle);
	}

	private void enableDragCursor(final Circle circle) {
		circle.setOnMouseEntered(e -> {
			if (!e.isPrimaryButtonDown()) {
				circle.getScene().setCursor(Cursor.HAND);
			}
		});
		circle.setOnMouseExited(e -> {
			if (!e.isPrimaryButtonDown()) {
				circle.getScene().setCursor(Cursor.DEFAULT);
			}
		});
	}

	protected abstract void setPoint(String name);

	protected abstract String getPoint();

	protected abstract LevelType getLevelType();

}
