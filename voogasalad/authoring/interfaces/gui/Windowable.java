package authoring.interfaces.gui;

import javafx.scene.Node;

public interface Windowable {
    /**
     * This Interface manages the GUI's relationship to the UIManager, 
     * allowing the manager to remain agnostic to the component 
     * implemented by only caring about the Node passed through to the manager.
     * 
     */
    Node getWindow();
}
