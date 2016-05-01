package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.Builder;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.interfaces.model.EditElementable;
import player.gamedisplay.Menuable;

/**
 * Player Menu Item to create a new Game Object.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class NewGameObjectNewItem extends MenuItemHandler {
	
	EditElementable myManager;
	
	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public NewGameObjectNewItem(Menuable model) {

		super();
		myManager = (EditElementable) model.getManager();
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		Builder popup = new GameObjectBuilder(myManager);
		popup.setTitle("New Game Object");
		popup.show();
	}

}
