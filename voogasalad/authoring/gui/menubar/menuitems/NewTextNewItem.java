package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.TextObjectBuilder;
import authoring.interfaces.model.EditElementable;
import player.gamedisplay.Menuable;

public class NewTextNewItem extends MenuItemHandler {
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
	public NewTextNewItem(Menuable model) {

		super();
		myManager = (EditElementable) model.getManager();

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
