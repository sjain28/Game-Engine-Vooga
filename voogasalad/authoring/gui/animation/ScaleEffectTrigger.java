package authoring.gui.animation;

import authoring.CustomText;

public class ScaleEffectTrigger extends CheckBoxTrigger implements Trigger {

	private static final String TITLE = "Scale Effect (Trigger)";
	
	@Override
	protected void nameCheckBox() {
		this.getChildren().addAll(select, new CustomText(TITLE));
	}

}
