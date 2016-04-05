package tools;

/**
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo, Hunter Lee
 *
 */
public class Vector {

	/**
	 * Vector class that keeps information about (x, y) coordinates as well as
	 * previous coordinates
	 * 
	 * Whenever current coordinates are updated, previous coordinates are updated
	 * accordingly
	 * 
	 */
	private double xPrev = 0;
	private double yPrev = 0;
	private double x;
	private double y;

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void setX(double x){
		this.xPrev = getX();
		this.x = x;
	}

	public void setY(double y){
		this.yPrev = getY();
		this.y = y;
	}

	public void addX(double dx){
		this.xPrev = getX();
		x+=dx;
	}

	public void addY(double dy){
		this.yPrev = getY();
		y+=dy;
	}

	public void addVector(Vector v){
		this.xPrev = getX();
		this.yPrev = getY();
		x+=v.getX();
		y+=v.getY();
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
