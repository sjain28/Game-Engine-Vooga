package tools;

/**
 * Velocity class that represents Sprite's velocity
 * that contains both magnitude and direction
 * 
 * @author Hunter Lee
 *
 */
public class Velocity extends Vector {

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
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
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
		double angle = 180.0 / Math.PI * Math.atan2(getY(), getX());
		return (angle > 0) ? angle : angle + 360.0;
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
		return (angle > 0) ? angle : angle + 2 * Math.PI;
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
		setX(Math.cos(angle) * magnitude);
		setY(Math.sin(angle) * magnitude);
	}
	
	/**
	 * For unit testing only
	 * 
	 */
	public static void main(String args[]){
		
		Velocity v1 = new Velocity(1, 1);
		Velocity v2 = new Velocity(1, 0);
		Velocity v3 = new Velocity(-1, -1);
		
		System.out.println("v1's Magnitude: " + v1.getMagnitude());
		System.out.println("v1's Direction angle: " + v1.getAngleDegree());
		
		System.out.println("v2's Magnitude: " + v2.getMagnitude());
		System.out.println("v2's Direction angle: " + v2.getAngleDegree());
		
		System.out.println("v3's Magnitude: " + v3.getMagnitude());
		System.out.println("v3's Direction angle: " + v3.getAngleDegree());
		
	}

}
