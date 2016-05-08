package authoring.gui.animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public abstract class TextFieldTrigger extends HBox implements Trigger {
	
	protected TextField field;
	
	@Override
	public void initialize() {
		specifyField();
	}
	
	protected abstract void specifyField();

	@Override
	public void onActivation(EventHandler<ActionEvent> action) {
		field.setOnAction(action);
	}

}
