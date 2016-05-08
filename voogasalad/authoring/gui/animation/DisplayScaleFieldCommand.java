// This entire file is part of my masterpiece.
// Aditya Srinivasan
package authoring.gui.animation;

import authoring.Command;
import authoring.CustomText;
import authoring.gui.items.NumberTextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.GUIUtils;
import tools.Pair;

/**
 * An implementation of the command interface. This is associated with the ScaleEffectTrigger, and is displayed upon checking
 * the scale CheckBox. This is done through the mediator, without knowledge of the other component. This maintains loose coupling.
 * 
 * @author Aditya Srinivasan
 *
 */
public class DisplayScaleFieldCommand implements Command, Detailable {
	
	private static final String LABEL = "Scale: ";
	private static final String FIELD = "scale";
	
	private VBox container;
	private HBox row;
	private NumberTextField scale;
	
	public DisplayScaleFieldCommand(Mediator mediator, VBox container) {
		this.container = container;
		this.scale = new NumberTextField();
		this.row = GUIUtils.makeRow(new CustomText(LABEL), scale);
	}

	@Override
	public void execute() {
		if(container.getChildren().contains(row)) {
			container.getChildren().remove(row);
		} else {
			container.getChildren().add(row);
		}
	}

	@Override
	public Pair<String, Object> getDetails() {
		if(!scale.getText().isEmpty()) {
			return new Pair<>(FIELD, Double.parseDouble(this.scale.getText()));
		}
		return null;
	}
}
