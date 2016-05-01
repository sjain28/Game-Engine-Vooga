package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import player.gamedisplay.Menuable;

/**
 * File Menu Item to create a new scene.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class NewSceneNewItem extends MenuItemHandler {

	Menuable myUIManager;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public NewSceneNewItem(Menuable model) {

		super();
		myUIManager = model;
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		myUIManager.addScene();
	}

}
