package authoring.gui.menubar;

import tools.VoogaException;

/**
 * Authoring Menu Item super class to handle authoring menu items.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public abstract class AuthoringMenuItemHandler extends MenuItemHandler {

	/**
	 * Handles the function of the menu item.
	 */
	abstract public void handle() throws VoogaException;

}
