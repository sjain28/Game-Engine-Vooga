package authoring.gui.items;

import javafx.scene.control.TextField;


/**
 * A custom text field that only accepts numbers.
 * 
 * @author Aditya Srinivasan, Arjun Desai
 *
 */
public class NumberTextField extends TextField {

	/**
	 * Ensures that the text field value is a positive integer.
	 */
	public void sanitizeForInteger() {
		this.textProperty().addListener((obs, old, n) -> {
			if(!n.matches("\\d+") && !n.isEmpty()) {
				this.setText(old);
			}
		});
	}

	/**
	 * Replaces unwanted text with a desired text.
	 */
	@Override
	public void replaceText (int start, int end, String text) {
		if (validate(text)) {
			super.replaceText(start, end, text);
		}
	}

	/**
	 * Replaces the selection with specified text.
	 */
	@Override
	public void replaceSelection (String text) {
		if (validate(text)) {
			super.replaceSelection(text);
		}
	}

	/**
	 * Checks if the text is valid.
	 * 
	 * @param text
	 * @return true if character is a digit or period
	 */
	private boolean validate (String text) {
		return text.matches("[-0-9.]*");
	}

}
