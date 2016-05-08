// This entire file is part of my masterpiece.
// Aditya Srinivasan

package authoring.gui.animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * A simple implementation of a trigger. It specifies the activation event to be associated with clicking the button. This
 * has the effect of triggering the command upon clicking the button.
 * @author Aditya Srinivasan
 *
 */
public class OKTrigger extends Button implements Trigger {
	
	private static final String TEXT = "OK";

	@Override
	public void initialize() {
		this.setText(TEXT);
	}

	@Override
	public void onActivation(EventHandler<ActionEvent> action) {
		this.setOnAction(action);
	}

}
