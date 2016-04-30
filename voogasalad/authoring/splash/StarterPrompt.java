package authoring.splash;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
