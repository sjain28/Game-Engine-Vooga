package authoring.gui.cartography;

import authoring.interfaces.Elementable;
import authoring.model.ElementSelectionModel;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Entrypoint extends Circle {
	
	private String entryLevel;
	
	private Entrypoint() {
		super(0, Color.LIGHTGREEN);
		this.setStroke(Color.WHITE);
		this.setStrokeWidth(3);
		enableDrag(this);
	}
	
	private static class SingletonHolder {
		private static final Entrypoint INSTANCE = new Entrypoint();
	}
	
	public static Entrypoint getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private void enableDrag(final Circle circle) {
		final Delta dragDelta = new Delta();
		circle.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				dragDelta.x = circle.getCenterX() - mouseEvent.getX();
				dragDelta.y = circle.getCenterY() - mouseEvent.getY();
				circle.getScene().setCursor(Cursor.MOVE);
			}
		});
		circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				circle.getScene().setCursor(Cursor.HAND);
			}
		});
		circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				circle.setCenterX(mouseEvent.getX() + dragDelta.x);
				circle.setCenterY(mouseEvent.getY() + dragDelta.y);
			}
		});
		circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					circle.getScene().setCursor(Cursor.HAND);
				}
			}
		});
		circle.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					circle.getScene().setCursor(Cursor.DEFAULT);
				}
			}
		});
	}

	public void setEntrypoint(String name) {
		this.entryLevel = name;
	}
	
	public String getEntrypoint() {
		return this.entryLevel;
	}

}
