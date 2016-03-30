package authoring.interfaces;

import tools.Vector;

/**
 * An interface implemented by GameObject that defines how
 * movement is specified.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public interface Moveable {
    /*
     * Allows for setting and getting of the movement Vectors (ie the velocity) of an object
     */
   public Vector getMovement();
   
   public void SetMovement(Vector v);
}
