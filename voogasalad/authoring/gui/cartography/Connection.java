package authoring.gui.cartography;

import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Connection extends Group {

	private static final double LINE_WIDTH = 5;
	private static final double END_X = 100;
	private static final double END_Y = -100;

	private Line connector;
	private Anchor anchor1;
	private Anchor anchor2;
	
	private String start;
	private String end;

	public Connection(CompleteAuthoringModelable model) {
		initializeLine(model);
		attachAnchors();

		this.getChildren().addAll(connector, anchor1, anchor2);
	}

	private void initializeLine(CompleteAuthoringModelable model) {
		connector = new Line(0, 0, END_X, END_Y);
		connector.setStrokeWidth(LINE_WIDTH);
		connector.setStroke(Paint.valueOf("white"));
		connector.setOnMouseClicked(e -> {
			if(e.getClickCount() == 2) {
				new ConnectionPrompt(start, end, model);
			}
		});
	}
	
	public Anchor getStartAnchor() {
		return this.anchor1;
	}
	
	public Anchor getEndAnchor() {
		return this.anchor2;
	}
	
	private void attachAnchors() {
		anchor1 = new Anchor(connector.startXProperty(), connector.startYProperty(), TransitionOrder.FIRST);
		anchor2 = new Anchor(connector.endXProperty(), connector.endYProperty(), TransitionOrder.LAST);

		enableDrag(anchor1);
		enableDrag(anchor2);
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

	public void setStartpoint(String name) {
		this.start = name;
	}
	
	public void setEndpoint(String name) {
		this.end = name;
	}
	

}
