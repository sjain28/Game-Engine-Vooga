package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.TextObjectBuilder;
import authoring.interfaces.model.EditElementable;
import player.gamedisplay.Menuable;

/**
 * File Menu Item to create a new text item.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

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
	 * Create new text
	 */
	@Override
	public void handle() {
		TextObjectBuilder initializer = new TextObjectBuilder(myManager);
		initializer.setTitle("New Text Object");
		initializer.show();
	}

}
