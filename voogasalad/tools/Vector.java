package tools;

/**
 * Vector class that keeps information about (x, y) coordinates as well as
 * previous coordinates
 * 
 * Whenever current coordinates are updated, previous coordinates are updated
 * accordingly
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo, Hunter Lee
 *
 */
public abstract class Vector {

	protected double xPrev = 0;
	protected double yPrev = 0;
	protected double x;
	protected double y;

	private static final double TWO = 2;
	/**
	 * Default constructors
	 * 
	 * @param x
	 * @param y
	 */
	public Vector (double x, double y) {
		setXY(x, y);
	}

	public Vector() {
		setXY(0, 0);
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
	
	/**
	 * @return the magnitude of a vector
	 */
	public double getMagnitude(){
		return Math.sqrt(Math.pow(getX() - getxPrev(), TWO) + Math.pow(getY() - getyPrev(), TWO));
	}

}
