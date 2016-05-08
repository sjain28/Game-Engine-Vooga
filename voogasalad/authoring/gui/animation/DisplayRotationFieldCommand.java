package authoring.gui.animation;

import authoring.Command;
import authoring.CustomText;
import authoring.gui.items.NumberTextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.GUIUtils;
import tools.Pair;

public class DisplayRotationFieldCommand implements Command, Detailable {
	
	private static final String CYCLES = "Cycles: ";
	private static final String ANGLE = "Angle: ";
	private static final String FIELD = "rotation";
	private static final double DEGREES_IN_CIRCLE = 360;

	private VBox container;
	private HBox row;
	private NumberTextField numRotations;
	private NumberTextField modAngle;

	public DisplayRotationFieldCommand(Mediator mediator, VBox container) {
		this.container = container;
		this.numRotations = new NumberTextField();
		this.modAngle = new NumberTextField();
		this.row = GUIUtils.makeRow(new CustomText(CYCLES), numRotations, new CustomText(ANGLE), modAngle);
	}

	@Override
	public void execute() {
		if (container.getChildren().contains(row)) {
			container.getChildren().remove(row);
		} else {
			container.getChildren().add(row);
		}
	}

	@Override
	public Pair<String, Object> getDetails() {
		if (!this.numRotations.getText().isEmpty() && !this.modAngle.getText().isEmpty()) {
			Double cycles = Double.parseDouble(this.numRotations.getText());
			Double angle = Double.parseDouble(this.modAngle.getText());
			return new Pair<>(FIELD, DEGREES_IN_CIRCLE * cycles + angle);
		}
		return null;
	}
}
