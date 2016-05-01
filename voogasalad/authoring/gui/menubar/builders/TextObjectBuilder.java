package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;
import authoring.model.VoogaFrontEndText;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Builder to create text objects.
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */

public class TextObjectBuilder extends Builder {

	private VBox container;
	private TextField text;
	private VoogaFrontEndText returnText;

	/**
	 * Initialization and connecting to editor to store object.
	 * @param editor
	 */
	public TextObjectBuilder (EditElementable editor) {
		super(editor);
		this.text = new TextField();
		returnText = new VoogaFrontEndText();
		populate();
		load(this.container);
	}

	/**
	 * Constructor with addition of text to add.
	 * @param editor
	 * @param text
	 */
	public TextObjectBuilder (EditElementable editor, VoogaFrontEndText text) {
		this(editor);
		returnText = text;
	}

	/**
	 * Population of the dialog box.
	 */
	private void populate () {
		this.container = new VBox();
		container.setSpacing(SPACING);
		container.getChildren().addAll(makeInfo("Text:", "Enter text...", text),
				makeButtons());
	}

	/**
	 * Compiling the text.
	 */
	@Override
	public void compile () {

		returnText.setText(this.text.getText());
		myManager.addGameElements(returnText);
		quit();
	}

	/**
	 * Compile status for debugging.
	 */
	@Override
	public boolean compileStatus () {
		return compile;
	}

}
