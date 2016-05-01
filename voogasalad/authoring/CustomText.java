package authoring;

/**
 * This class allows for standardized text settings, so that whenever new text needs to be
 * made, it will automatically be formatted all the same throughout the GUI. Through the use
 * of a resource file, these standardized fonts can be modified by the user if they wish to 
 * create a different display.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 */

import java.util.ResourceBundle;

/**
 * The Custom Text file allows for strings to be standardized with a constant color, font,
 * and font size. Anytime we use fonts, we call upon custom text and it will automatically format
 * the text to match the rest of the GUI.
 * 
 * @author Harry Guo, Aditya Srinivasan, Arjun Desai, Nick Lockett
 */

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import resources.VoogaBundles;

public class CustomText extends Text {

	private ResourceBundle textProperties;

	private String COLOR;
	private String FONT;
	private double FONT_SIZE;

	/**
	 * new Object to allow for easy standardized text settings, like a constant font, color etc
	 * 
	 * @param text: text to edit
	 */
	public CustomText(String text) {
		super(text);
		textProperties = VoogaBundles.textProperties;
		COLOR = textProperties.getString("color");
		FONT = textProperties.getString("font");
		FONT_SIZE = Double.parseDouble(textProperties.getString("fontSize"));
		this.setFill(Paint.valueOf(COLOR));
	}

	/**
	 * Custom text with user defined fontSize
	 * 
	 * @param text: text to edit
	 * @param fontSize: font size to edit
	 */
	public CustomText(String text, double fontSize) {
		this(text);
		this.setFont(Font.font(fontSize));
	}

	/**
	 * Custom text with user defined font weight
	 * 
	 * @param text: text to edit
	 * @param weight: font weight to edit
	 */
	public CustomText(String text, FontWeight weight) {
		this(text);
		this.setFont(Font.font(FONT, weight, FONT_SIZE));
	}

	/**
	 * Custom text with user defined font
	 * 
	 * @param text: text to edit
	 * @param weight: font weight to edit
	 * @param fontSize: font size to edit
	 */
	public CustomText(String text, FontWeight weight, double fontSize) {
		this(text);
		this.setFont(Font.font(FONT, weight, fontSize));
	}

}
