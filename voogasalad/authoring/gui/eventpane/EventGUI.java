package authoring.gui.eventpane;

import javafx.scene.Node;
import tools.VoogaException;

/**
 * GUI representation of the different causes and effects specified in the events package
 * 
 * @author Arjun Desai
 *
 */
public interface EventGUI {
    
    /**
     * Called by EventWindow to populate GUI
     * Creates a Region that contains children that is added to the scene in EventWindow
     * 
     * @return Node to display on the EventWindow
     */
    Node display();
    
    /**
     * Gets string that details the event class path and other details that are specified for the constructor of the specific cause
     * or effect that the class implementing this method has
     * 
     * Example: see AnimationEffect
     * 
     * Note: Commas are used as delimiters - please use commas as the delimiter
     * 
     * @return string representation of details needed to construct the specified
     * @throws VoogaException
     */
    String getDetails() throws VoogaException;
}
