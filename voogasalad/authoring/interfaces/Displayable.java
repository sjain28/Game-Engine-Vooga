package authoring.interfaces;

import javafx.scene.Node;

/**
 * An interface implemented by all game elements that defines how
 * depth is specified.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public interface Displayable {

    public String getID();
    public Node getNodeObject();
}
