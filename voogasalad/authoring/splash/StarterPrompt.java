package authoring.splash;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * SuperClass to define Screen Transitions
 */
public abstract class StarterPrompt extends Stage {
	
	protected static final double SPACING = 10;
	protected static final double HEADER_SIZE = 30;

	public StarterPrompt() {
		initializeContainer();
		setTheScene();
	}
	
	protected abstract void setProceedEvent(EventHandler<ActionEvent> proceedEvent);
	
	protected abstract void initializeContainer();
	
	protected abstract void setTheScene();
	
}
