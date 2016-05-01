package authoring.interfaces;

import tools.Vector;
import tools.Velocity;

/**
 * An interface implemented by GameObject that defines how
 * movement is specified.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public interface Moveable {

	/**
	 * Allows for the getting of the movement Vectors (ie the velocity) of an object
	 */
	Vector getVelocity();

	/**
	 * Allows for the setting of the movement Vectors of an object
	 * 
	 * @param velocity
	 */
	void setVelocity(Velocity velocity);
}
