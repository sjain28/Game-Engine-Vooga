package tools;

/**
 * Velocity class that represents Sprite's velocity
 * that contains both magnitude and direction
 * 
 * @author Hunter Lee
 *
 */
public class Velocity extends Vector {
	
	private static final double HALF_TURN = 180;
	private static final double FULL_TURN = 360;
	private static final double TWO = 2;
	/**
	 * Default constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Velocity(double x, double y) {
		super(x, y);
	}
	
	/**
	 * Computes and returns the magnitude of the velocity
	 * 
	 * @return magnitude
	 */
	public double getMagnitude() {
		return Math.sqrt(Math.pow(getX(),TWO) + Math.pow(getY(), TWO));
	}
	
	/**
	 * Computes and returns the direction of the velocity
	 * Based upon the traditional Cartesian coordinate system
	 * 
	 * Direction ranges from 0 to 360
	 * 
	 * @return direction
	 */
	public double getAngleDegree() {
		double angle = HALF_TURN / Math.PI * Math.atan2(getY(), getX());
		return (angle > 0) ? angle : angle + FULL_TURN;
	}
	
	/**
	 * Computes and returns the direction of the velocity
	 * Based upon the traditional Cartesian coordinate system
	 * 
	 * Direction is in radians
	 * 
	 * @return direction
	 */
	public double getAngleRadian() {
		double angle = Math.atan2(getY(), getX());
		return (angle > 0) ? angle : angle + TWO * Math.PI;
	}
	
	/**
	 * Computes X and Y values given magnitude and angle and sets them
	 * 
	 * Angle is in degrees (0-360)
	 * 
	 * @param magnitude
	 * @param angle
	 */
	public void setVelocity(Double magnitude, Double angle) {
		setX(Math.cos(Math.toRadians(angle)) * magnitude);
		setY(Math.sin(Math.toRadians(angle)) * magnitude);
	}

	

}
