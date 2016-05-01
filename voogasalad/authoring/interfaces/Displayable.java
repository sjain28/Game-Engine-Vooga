package authoring.interfaces;

import javafx.scene.Node;

/**
 * An interface implemented by all game elements that defines how they are displayed.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public interface Displayable {

	/**
	 * Gets the ID of the object
	 * @return
	 */
    public String getID();
    
    /**
     * Gets the node object form of the object
     * @return
     */
    public Node getNodeObject();
}
