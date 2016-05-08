package authoring.gui.animation;

import authoring.CustomText;

public class RotationEffectTrigger extends CheckBoxTrigger implements Trigger {
	
	private static final String TITLE = "Rotation Effect (Trigger)";

	@Override
	protected void nameCheckBox() {
		this.getChildren().addAll(select, new CustomText(TITLE));
	}

}
