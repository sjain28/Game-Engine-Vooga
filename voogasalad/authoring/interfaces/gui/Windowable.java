package authoring.interfaces.gui;

import javafx.scene.Node;

/**
 * This Interface manages the GUI's relationship to the UIManager, 
 * allowing the manager to remain agnostic to the component 
 * implemented by only caring about the Node passed through to the manager.
 * 
 * @author Arjun Desai, Aditya Srinivasan, Harry Guo, Nick Lockett
 */

public interface Windowable {
	
    /**
     * This Interface manages the GUI's relationship to the UIManager, 
     * allowing the manager to remain agnostic to the component 
     * implemented by only caring about the Node passed through to the manager.
     * 
     */
    Node getWindow();

}
