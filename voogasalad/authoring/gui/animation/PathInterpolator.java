package authoring.gui.animation;

/**
 * Interpolates a path and determines the coordinates that comprise the shape.
 * Uses an approximation but samples at an extremely high rate in order to
 * minimize uncertainty.
 * 
 * @author adityasrinivasan
 *
 */
public class PathInterpolator {

	/**
	 * Constants
	 */
	private static final double NUM_POINTS = 6000;
	private static final int GRANULARITY_FACTOR = 3;
	private static final int GRANULARITY_POWER = 2;
	private Double[] xInterpolation;
	private Double[] yInterpolation;

	/**
	 * Initializes the arrays holding interpolation points.
	 */
	public PathInterpolator() {
		xInterpolation = new Double[(int) NUM_POINTS];
		yInterpolation = new Double[(int) NUM_POINTS];
	}

	/**
	 * Interpolates given x and y controls to the granularity specified in the
	 * constants above.
	 * 
	 * @param xRaw
	 * @param yRaw
	 */
	public void interpolate(Double[] xRaw, Double[] yRaw) {
		int i = 0;
		for (double t = 0.00; t < 1.00; t = t + 1 / NUM_POINTS) {
			if (i < NUM_POINTS) {
				xInterpolation[i] = Math.pow((1 - t), GRANULARITY_FACTOR) * xRaw[0]
						+ GRANULARITY_FACTOR * Math.pow((1 - t), GRANULARITY_POWER) * t * xRaw[1]
						+ GRANULARITY_FACTOR * (1 - t) * Math.pow(t, GRANULARITY_POWER) * xRaw[GRANULARITY_POWER]
						+ Math.pow(t, GRANULARITY_FACTOR) * xRaw[GRANULARITY_FACTOR];
				yInterpolation[i] = Math.pow((1 - t), GRANULARITY_FACTOR) * yRaw[0]
						+ GRANULARITY_FACTOR * Math.pow((1 - t), GRANULARITY_POWER) * t * yRaw[1]
						+ GRANULARITY_FACTOR * (1 - t) * Math.pow(t, GRANULARITY_POWER) * yRaw[GRANULARITY_POWER]
						+ Math.pow(t, GRANULARITY_FACTOR) * yRaw[GRANULARITY_FACTOR];
				i++;
			}
		}
		for (Double d : xInterpolation) {
//			System.out.println(d);
		}
//		System.out.println("-------------");
		for (Double d : yInterpolation) {
//			System.out.println(d);
		}
	}

	/**
	 * Returns the list of interpolation X points.
	 * 
	 * @return an array of doubles.
	 */
	public Double[] getXInterpolation() {
		return this.xInterpolation;
	}

	/**
	 * Returns the list of interpolation Y points.
	 * 
	 * @return an array of doubles.
	 */
	public Double[] getYInterpolation() {
		return this.yInterpolation;
	}

}
