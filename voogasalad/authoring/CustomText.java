package authoring;

import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class CustomText extends Text {
	
	private static final String COLOR = "WHITE";
	
	public CustomText(String text) {
		super(text);
		this.setFill(Paint.valueOf(COLOR));
	}

}
