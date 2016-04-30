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
	private static final int COMPLETE_COUNT = 2;

	private transient Line connector;
	private transient Anchor anchor1;
	private transient Anchor anchor2;
	
	private double startx, starty, endx, endy;
	
	private Level start;
	private Level end;

	public Connection(CompleteAuthoringModelable model, double startx, double starty, double endx, double endy) {
		this(startx, starty, endx, endy);
		initializeModel(model);
	}

	public Connection(double startx, double starty, double endx, double endy) {
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
		initializeLine();
		attachAnchors();
		this.getChildren().addAll(connector, anchor1, anchor2);
	}
	
	private void initializeLine() {
		connector = new Line(startx, starty, endx, endy);
		connector.setStrokeWidth(LINE_WIDTH);
		connector.setStroke(Paint.valueOf("white"));
	}

	private void initializeModel(CompleteAuthoringModelable model) {
		connector.setOnMouseClicked(e -> {
			if(e.getClickCount() == COMPLETE_COUNT) {
				new ConnectionPrompt(start.getName(), end.getName(), model);
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
		enableDragCursor(circle);
	}
	
	private void enableDragCursor(final Circle circle) {
		
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

	public void setStartpoint(Level start) {
		this.start = start;
	}
	
	public void setEndpoint(Level end) {
		this.end = end;
	}
	
	public Level getStartpoint() {
		return this.start;
	}
	
	public Level getEndpoint() {
		return this.end;
	}
	
	public Line getLine() {
		return this.connector;
	}
	

}
