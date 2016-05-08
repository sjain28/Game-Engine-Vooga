package authoring.gui.animation;

import authoring.CustomText;

public class PathEffectTrigger extends CheckBoxTrigger implements Trigger {

	private static final String TITLE = "Path Effect (Trigger)";
	
	@Override
	protected void nameCheckBox() {
		this.getChildren().addAll(select, new CustomText(TITLE));
	}

}