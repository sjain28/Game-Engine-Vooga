package authoring.gui.animation;

import authoring.gui.menubar.builders.Anchor;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

public class BezierCurve extends Group {
	
	private CubicCurve curve;

	public BezierCurve() {
		curve = createStartingCurve();
		initializeControls();
	}

	CubicCurve createStartingCurve() {
		curve = new CubicCurve();
		curve.setStartX(100);
		curve.setStartY(100);
		curve.setControlX1(150);
		curve.setControlY1(50);
		curve.setControlX2(250);
		curve.setControlY2(150);
		curve.setEndX(300);
		curve.setEndY(100);
		curve.setStroke(Color.FORESTGREEN);
		curve.setStrokeWidth(4);
		curve.setStrokeLineCap(StrokeLineCap.ROUND);
		curve.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
		this.getChildren().add(curve);
		return curve;
	}

	private void initializeControls() {
		Line controlLine1 = new BoundLine(curve.controlX1Property(), curve.controlY1Property(), curve.startXProperty(),
				curve.startYProperty());
		Line controlLine2 = new BoundLine(curve.controlX2Property(), curve.controlY2Property(), curve.endXProperty(),
				curve.endYProperty());
		Anchor start = new Anchor(Color.PALEGREEN, curve.startXProperty(), curve.startYProperty());
		Anchor control1 = new Anchor(Color.GOLD, curve.controlX1Property(), curve.controlY1Property());
		Anchor control2 = new Anchor(Color.GOLDENROD, curve.controlX2Property(), curve.controlY2Property());
		Anchor end = new Anchor(Color.TOMATO, curve.endXProperty(), curve.endYProperty());
		this.getChildren().addAll(controlLine1, controlLine2, start, control1, control2, end);
	}
	
	public CubicCurve getCurve() {
		return this.curve;
	}
	
	public double[] getXPoints() {
		System.out.println(curve.getEndX());
		return new double[]{curve.getStartX(), curve.getControlX1(), curve.getControlX2(), curve.getEndX()};
	}
	
	public double[] getYPoints() {
		System.out.println(curve.getEndY());
		return new double[]{curve.getStartY(), curve.getControlY1(), curve.getControlY2(), curve.getEndY()};
	}

}
