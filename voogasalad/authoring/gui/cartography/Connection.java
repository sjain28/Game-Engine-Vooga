package authoring.gui.cartography;

import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import tools.VoogaAlert;

/**
 * Class for creating the connection bridge between levels.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */
public class Connection extends Group {

	/**
	 * Private instance variables.
	 */
	private static final double LINE_WIDTH = 5;
	private static final int DOUBLE_CLICK = 2;

	private transient Line connector;
	private transient Anchor anchor1;
	private transient Anchor anchor2;

	private double startx, starty, endx, endy;

	private Level start;
	private Level end;

	/**
	 * Constructor to s
	 * @param model
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 */
	public Connection(CompleteAuthoringModelable model, double startx, double starty, double endx, double endy) {
		this(startx, starty, endx, endy);
		initializeModel(model);
	}

	/**
	 * Constructor to initializes and build the line.
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 */
	public Connection(double startx, double starty, double endx, double endy) {
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
		initializeLine();
		attachAnchors();
		this.getChildren().addAll(connector, anchor1, anchor2);
	}

	/**
	 * Initializes the constructor line.
	 */
	private void initializeLine() {
		connector = new Line(startx, starty, endx, endy);
		connector.setStrokeWidth(LINE_WIDTH);
		connector.setStroke(Paint.valueOf("white"));
	}

	/**
	 * Initializes the model
	 * @param model
	 */
	private void initializeModel(CompleteAuthoringModelable model) {
		connector.setOnMouseClicked(e -> {
			if(e.getClickCount() == DOUBLE_CLICK) {
				if(model.getName().equals(start.getName())) {
					ConnectionPrompt prompt = new ConnectionPrompt(start.getName(), end.getName(), model);
					prompt.show();
				} else {
					VoogaAlert alert = new VoogaAlert("You must define transitions from the level tab you are authoring.");
					alert.showAndWait();
				}
			}
		});
	}

	/**
	 * Gets the starting anchor.
	 * @return
	 */
	public Anchor getStartAnchor() {
		return this.anchor1;
	}

	/**
	 * Get the ending anchor.
	 * @return
	 */
	public Anchor getEndAnchor() {
		return this.anchor2;
	}

	/**
	 * Attaches anchors.
	 */
	private void attachAnchors() {
		anchor1 = new Anchor(connector.startXProperty(), connector.startYProperty(), TransitionOrder.FIRST);
		anchor2 = new Anchor(connector.endXProperty(), connector.endYProperty(), TransitionOrder.LAST);

		enableDrag(anchor1);
		enableDrag(anchor2);
	}

	/**
	 * Enables the drag effect.
	 * @param circle
	 */
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

	/**
	 * Enables the Drag Cursor.
	 * @param circle
	 */
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

	/**
	 * Set the start point with a level
	 * @param start
	 */
	public void setStartpoint(Level start) {
		this.start = start;
	}

	/**
	 * Set and ending level
	 * @param end
	 */
	public void setEndpoint(Level end) {
		this.end = end;
	}

	/**
	 * Get the level with the start point 
	 * @return
	 */
	public Level getStartpoint() {
		return this.start;
	}

	/**
	 * Get the level with the end point.
	 * @return
	 */
	public Level getEndpoint() {
		return this.end;
	}

	/**
	 * Get the connecting line.
	 * @return
	 */
	public Line getLine() {
		return this.connector;
	}


}
