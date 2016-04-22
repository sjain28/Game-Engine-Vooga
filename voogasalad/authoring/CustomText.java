package authoring;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CustomText extends Text {
	
	private static final String COLOR = "WHITE";
	private static final String FONT = "Segoe UI Semibold";
	
	/**
	 * new Object to allow for easy standardized text settings, like a constant font, color etc
	 * 
	 * @param text: text to edit
	 */
	public CustomText(String text) {
		super(text);
		this.setFill(Paint.valueOf(COLOR));
	}
	
	public CustomText(String text, double fontSize) {
		this(text);
		this.setFont(Font.font(fontSize));
	}
	
	public CustomText(String text, FontWeight weight, double fontSize) {
		this(text);
		this.setFont(Font.font(FONT, weight, fontSize));
	}

}
