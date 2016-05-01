package authoring.gui.menubar.builders;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.interfaces.model.EditElementable;
import authoring.resourceutility.ButtonMaker;
import gameengine.SpriteFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tools.GUIUtils;
import tools.VoogaAlert;

/**
 * Builder Super class to handle the building of object to put into the game.
 * 
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */

public abstract class Builder extends Stage {

	/**
	 * private instance variables
	 */
	protected SpriteFactory mySpriteFactory;
	protected EditElementable myManager;
	protected static final double SPACING = 10;
	protected boolean compile;

	/**
	 * Sets up link with a manager class to communicate/load/store 
	 * information into back end.
	 * @param editor
	 */
	protected Builder (EditElementable editor) {
		if (editor != null) {
			mySpriteFactory = editor.getSpriteFactory();
			myManager = editor;
		}
	}

	/**
	 * Load Scene.
	 * @param region
	 */
	protected void load (Parent region) {
		Scene scene = new VoogaScene(region);
		this.setScene(scene);
	}

	/**
	 * Creation of OK and Cancel buttons.
	 * @return
	 */
	protected HBox makeButtons () {
		HBox buttons = new HBox();
		Button create;

		create = new ButtonMaker().makeButton("OK", e -> compile());

		Button cancel = new ButtonMaker().makeButton("Cancel", e -> quit());
		buttons.getChildren().addAll(create, cancel);
		return buttons;

	}

	/**
	 * Creates information to display in the dialog box.
	 * @param label
	 * @param prompt
	 * @param tf
	 * @return
	 */
	protected HBox makeInfo (String label, String prompt, TextField tf) {
		tf.setPromptText(prompt);
		return GUIUtils.makeRow(new CustomText(label), tf);
	}

	/**
	 * Close the dialog box.
	 */
	protected void quit () {
		this.close();
	}

	/**
	 * Method to display error with message.
	 * @param s
	 */
	protected void numberError (String s) {
		VoogaAlert alert = new VoogaAlert(s);
		alert.showAndWait();
	}

	/**
	 * Compiles the information gathered in the dialog box and processes it.
	 */
	public abstract void compile ();

	/**
	 * Checker method to see if builder compiled information
	 * -used for debugging performances
	 * @return
	 */
	public abstract boolean compileStatus ();

}
