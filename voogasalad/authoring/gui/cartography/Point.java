package authoring.gui.cartography;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Point class for the ends of the connection line.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */
public abstract class Point extends Circle {

	private static final int STROKE_WIDTH = 3;

	/**
	 * Instantiates point with radius and color.
	 * @param r
	 * @param c
	 */
	public Point(double r, Color c) {
		super(r, c);
		this.setStroke(Color.WHITE);
		this.setStrokeWidth(STROKE_WIDTH);
		enableDrag(this);
	}

	/**
	 * Enables the drag feature on the points.
	 * @param circle
	 */
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

	/**
	 * Enables the drag cursor.
	 * @param circle
	 */
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

	/**
	 * Sets the end point to a level name.
	 * @param name
	 */
	protected abstract void setPoint(String name);

	/**
	 * Gets the level name that the point is on.
	 * @return
	 */
	protected abstract String getPoint();

	/**
	 * Get the level type that the point defines.
	 * @return
	 */
	protected abstract LevelType getLevelType();

}
