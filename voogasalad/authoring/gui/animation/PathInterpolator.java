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
	
	/**
	 * Constants
	 */
	private static final int NUM_POINTS = 1000;
	private static final double INCREMENT = 0.001;
	
	private Double[] xInterpolation;
	private Double[] yInterpolation;
	
	/**
	 * Initializes the arrays holding interpolation points.
	 */
	public PathInterpolator() {
		xInterpolation = new Double[NUM_POINTS];
		yInterpolation = new Double[NUM_POINTS];
	}
	
	/**
	 * Interpolates given x and y controls to the granularity specified in the constants
	 * above.
	 * @param xRaw
	 * @param yRaw
	 */
	public void interpolate(Double[] xRaw, Double[] yRaw) {
		int i = 0;
		for (double t = 0.00; t < INCREMENT * NUM_POINTS ; t = t + INCREMENT) {
	         xInterpolation[i] = Math.pow((1-t), 3) * xRaw[0] + 3 * Math.pow((1-t), 2) * t * xRaw[1] + 3 * (1-t) * Math.pow(t, 2) * xRaw[2] + Math.pow(t, 3) * xRaw[3];
	         yInterpolation[i] = Math.pow((1-t), 3) * yRaw[0] + 3 * Math.pow((1-t), 2) * t * yRaw[1] + 3 * (1-t) * Math.pow(t, 2) * yRaw[2] + Math.pow(t, 3) * yRaw[3];
	         i++;
		}
	}
	
	/**
	 * Returns the list of interpolation X points.
	 * @return an array of doubles.
	 */
	public Double[] getXInterpolation() {
		return this.xInterpolation;
	}
	
	/**
	 * Returns the list of interpolation Y points.
	 * @return an array of doubles.
	 */
	public Double[] getYInterpolation() {
		return this.yInterpolation;
	}

}
