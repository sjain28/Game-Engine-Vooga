package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.TextObjectBuilder;
import authoring.gui.menubar.builders.VariableBuilder;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class NewVariableFileItem extends MenuItemHandler {
	private EditElementable myManager;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model
	 *            to interface backend interactions with the model
	 * @param event:
	 *            Unused vestige of previous poor programming. Should soon be
	 *            phased out.
	 */
	public NewVariableFileItem(Menuable model, Sceneable uiModel) {
		super();
		myManager = (EditElementable) model;
	}

	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		VariableBuilder initializer = new VariableBuilder(myManager);
		initializer.setTitle("New Variable");
		initializer.show();
	}

}
