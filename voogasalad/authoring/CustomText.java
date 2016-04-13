package authoring;

import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class CustomText extends Text {
	
	private static final String COLOR = "WHITE";
	/**
	 * new Object to allow for easy standardized text settings, like a constant font, color ect
	 * 
	 * @param text: text to edit
	 */
	public CustomText(String text) {
		super(text);
		this.setFill(Paint.valueOf(COLOR));
	}

}
