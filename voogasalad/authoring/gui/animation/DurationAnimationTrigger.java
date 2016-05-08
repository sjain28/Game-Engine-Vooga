package authoring.gui.animation;

import authoring.CustomText;
import authoring.gui.items.NumberTextField;
import tools.Pair;

public class DurationAnimationTrigger extends TextFieldTrigger implements Trigger, Detailable {
	
	private static final String LABEL = "Duration: ";
	private static final String FIELD = "duration";

	@Override
	protected void specifyField() {
		field = new NumberTextField();
		this.getChildren().addAll(new CustomText(LABEL), field);
	}

	@Override
	public Pair<String, Object> getDetails() {
		Integer duration = Integer.parseInt(field.getText());
		return new Pair<>(FIELD, duration);
	}

}
