// This entire file is part of my masterpiece.
// Aditya Srinivasan

package authoring.gui.animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

/**
 * This is an example of how the original `Trigger` interface can be extended to generalize a textfield trigger. A template
 * design pattern is used to allow subclasses to define the specific label, but all other functionality is kept here. This is
 * also done for TextField objects.
 * 
 * @author Aditya Srinivasan
 *
 */
public abstract class CheckBoxTrigger extends HBox implements Trigger {
	
	protected CheckBox select;

	@Override
	public void initialize() {
		select = new CheckBox();
		nameCheckBox();
	}
	
	protected abstract void nameCheckBox();

	@Override
	public void onActivation(EventHandler<ActionEvent> action) {
		select.selectedProperty().addListener((obs, old, n) -> {
			select.setOnAction(action);
		});
	}

}
