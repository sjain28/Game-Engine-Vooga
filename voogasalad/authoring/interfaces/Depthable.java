package authoring.interfaces;

/**
 * An interface implemented by all game elements that defines how
 * depth is specified.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public interface Depthable {

	/**
	 * Sets the depth of the Depthable
	 * 
	 * @param depth: z-axis location of the object on the screen
	 */
	public void setDepth(double depth);
}
