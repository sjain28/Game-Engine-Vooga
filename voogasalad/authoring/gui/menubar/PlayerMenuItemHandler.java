package authoring.gui.menubar;

import tools.VoogaException;

/**
 * Player Menu Item super class to handle player menu items.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public abstract class PlayerMenuItemHandler extends MenuItemHandler {

	/**
	 * Handles the function of the menu item.
	 * @throws VoogaException
	 */
	@Override
	public abstract void handle() throws VoogaException;

}
