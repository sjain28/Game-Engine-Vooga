package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.gui.menubar.builders.TextObjectBuilder;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class NewTextFileItem extends MenuItemHandler {
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
	public NewTextFileItem(Menuable model, EventHandler<InputEvent> event) {
		super();
		myManager = (EditElementable) model;

	}

	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		TextObjectBuilder initializer = new TextObjectBuilder(myManager);
		initializer.setTitle("New Text Object");
		initializer.show();
	}

}
