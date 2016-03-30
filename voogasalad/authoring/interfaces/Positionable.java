package authoring.interfaces;

import tools.Vector;

/**
 * An interface implemented by all game elements that defines how
 * position is specified.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public interface Positionable {
    /**
     * gets and sets the X values for the object (ie its position on "Game") as a vector
     */
    public void setPostion(Vector v);
    
    public Vector getPosition();
}
