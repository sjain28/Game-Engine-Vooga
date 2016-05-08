package authoring.gui.animation;

import authoring.CustomText;
import javafx.scene.control.TextField;
import tools.Pair;

public class NameAnimationTrigger extends TextFieldTrigger implements Trigger, Detailable {
	
	private static final String LABEL = "Name: ";
	private static final String FIELD = "name";

	@Override
	protected void specifyField() {
		field = new TextField();
		this.getChildren().addAll(new CustomText(LABEL), field);
	}

	@Override
	public Pair<String, Object> getDetails() {
		return new Pair<>(FIELD, field.getText());
	}

}
