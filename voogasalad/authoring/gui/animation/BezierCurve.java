package authoring.gui.animation;

import authoring.gui.menubar.builders.Anchor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 * This is a group of a CubicCurve, anchors, and lines that embody the Bezier curve. Intended to be used
 * as a group of nodes, but the particular curve can be extracted.
 * 
 * @author Aditya Srinivasan
 *
 */
public class BezierCurve extends Group {
	
	/**
	 * Constants
	 */
	private static final double START_X = 100;
	private static final double START_Y = 100;
	private static final double CONTROL_X1 = 150;
	private static final double CONTROL_Y1 = 50;
	private static final double CONTROL_X2 = 250;
	private static final double CONTROL_Y2 = 150;
	private static final double END_X = 300;
	private static final double END_Y = 100;
	private static final double WIDTH = 4;
	private static final double SATURATION_FACTOR = 1.2;
	private static final double OPACITY_FACTOR = 0.6;
	
	private CubicCurve curve;

	/**
	 * Creates the Bezier curve and the controls that allow it to be transformed.
	 */
	public BezierCurve() {
		curve = createStartingCurve();
		initializeControls();
	}

	/**
	 * Creates the starting Bezier curve and its controls.
	 * @return
	 */
	CubicCurve createStartingCurve() {
		curve = new CubicCurve();
		curve.setStartX(START_X);
		curve.setStartY(START_Y);
		curve.setControlX1(CONTROL_X1);
		curve.setControlY1(CONTROL_Y1);
		curve.setControlX2(CONTROL_X2);
		curve.setControlY2(CONTROL_Y2);
		curve.setEndX(END_X);
		curve.setEndY(END_Y);
		curve.setStroke(Color.CADETBLUE);
		curve.setStrokeWidth(WIDTH);
		curve.setStrokeLineCap(StrokeLineCap.ROUND);
		curve.setFill(Color.CORNSILK.deriveColor(0, SATURATION_FACTOR, 1, OPACITY_FACTOR));
		this.getChildren().add(curve);
		return curve;
	}

	/**
	 * Binds the controls to the position of the cubic curve, allowing it to be transformed dynamically.
	 */
	private void initializeControls() {
		Line controlLine1 = new BoundLine(curve.controlX1Property(), curve.controlY1Property(), curve.startXProperty(),
				curve.startYProperty());
		Line controlLine2 = new BoundLine(curve.controlX2Property(), curve.controlY2Property(), curve.endXProperty(),
				curve.endYProperty());
		Anchor start = new Anchor(Color.DARKSEAGREEN, curve.startXProperty(), curve.startYProperty());
		Anchor control1 = new Anchor(Color.GOLD, curve.controlX1Property(), curve.controlY1Property());
		Anchor control2 = new Anchor(Color.GOLDENROD, curve.controlX2Property(), curve.controlY2Property());
		Anchor end = new Anchor(Color.TOMATO, curve.endXProperty(), curve.endYProperty());
		this.getChildren().addAll(controlLine1, controlLine2, start, control1, control2, end);
	}
	
	/**
	 * Returns the cubic curve component of the group.
	 * @return a CubicCurve representing the Bezier curve.
	 */
	public CubicCurve getCurve() {
		return this.curve;
	}

}
