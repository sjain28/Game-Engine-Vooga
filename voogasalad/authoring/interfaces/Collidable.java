package authoring.interfaces;

/**
 * An interface implemented by GameObject that defines how
 * collision is specified.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public interface Collidable {
    
    /**
     * Method implemented for collision of two objects
     * 
     * @param object: Another collidable object
     */
    public void onCollide(Collidable object);
}
