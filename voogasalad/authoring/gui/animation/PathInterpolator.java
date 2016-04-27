package authoring.gui.animation;

import javafx.scene.shape.Shape;

/**
 * Interpolates a path and determines the coordinates that comprise the shape. Uses an approximation but samples
 * at an extremely high rate in order to minimize uncertainty.
 * 
 * @author adityasrinivasan
 *
 */
public class PathInterpolator {
	
	private double[] xInterpolation;
	private double[] yInterpolation;
	
	public PathInterpolator() {
		xInterpolation = new double[1000];
		yInterpolation = new double[1000];
	}
	
	public void interpolate(double[] xRaw, double[] yRaw) {
		int i = 0;
		for (double t = 0.00; t < 1.000; t = t + .001) {
	         xInterpolation[i] = Math.pow((1-t), 3) * xRaw[0] + 3 * Math.pow((1-t), 2) * t * xRaw[1] + 3 * (1-t) * Math.pow(t, 2) * xRaw[2] + Math.pow(t, 3) * xRaw[3];
	         yInterpolation[i] = Math.pow((1-t), 3) * yRaw[0] + 3 * Math.pow((1-t), 2) * t * yRaw[1] + 3 * (1-t) * Math.pow(t, 2) * yRaw[2] + Math.pow(t, 3) * yRaw[3];
	         i++;
		}
	}
	
	public double[] getXInterpolation() {
		return this.xInterpolation;
	}
	
	public double[] getYInterpolation() {
		return this.yInterpolation;
	}

}
