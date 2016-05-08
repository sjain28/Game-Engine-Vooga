// This entire file is part of my masterpiece.
// Aditya Srinivasan

package authoring.gui.animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * The `Trigger` interface used to define GUI components that act as triggers for other events.
 * @author Aditya Srinivasan
 */
public interface Trigger {
	
	public void initialize();
	
	public void onActivation(EventHandler<ActionEvent> action);
	
}
