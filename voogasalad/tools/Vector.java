package tools;

/**
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo, Hunter Lee
 *
 */
public class Vector {

	private double xPrev = 0;
	private double yPrev = 0;
	private double x;
	private double y;
	
	/**
	 * Vector class that keeps information about (x, y) coordinates as well as
	 * previous coordinates
	 * 
	 * Whenever current coordinates are updated, previous coordinates are updated
	 * accordingly
	 * 
	 */
	public Vector (double x, double y) {
		setXY(x, y);
	}

	/**
	 * Get the current x coordinate
	 * 
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the current y coordinate
	 * 
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * Give x a new value
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.xPrev = getX();
		this.x = x;
	}

	/**
	 * Give y a new value
	 * 
	 * @param y
	 */
	public void setY(double y) {
		this.yPrev = getY();
		this.y = y;
	}
	
	/**
	 * Sets x, y to a new set of values
	 * 
	 * @param x
	 * @param y
	 */
	public void setXY(double x, double y) {
		setX(x);
		setY(y);
	}

	/**
	 * Adds x coordinate
	 * 
	 * @param dx
	 */
	public void addX(double dx) {
		this.xPrev = getX();
		x += dx;
	}

	/**
	 * Adds y coordinate
	 * 
	 * @param dy
	 */
	public void addY(double dy) {
		this.yPrev = getY();
		y += dy;
	}

	/**
	 * Adds vector v to self
	 * 
	 * @param v
	 */
	public void addVector(Vector v) {
		this.xPrev = getX();
		this.yPrev = getY();
		x += v.getX();
		y += v.getY();
	}

	/**
	 * @return the xPrev
	 */
	public double getxPrev() {
		return xPrev;
	}

	/**
	 * @return the yPrev
	 */
	public double getyPrev() {
		return yPrev;
	}
}
