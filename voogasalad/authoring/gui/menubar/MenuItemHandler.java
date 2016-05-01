package authoring.gui.menubar;

import tools.VoogaException;

/**
 * Abstract class to handle menubar functionality.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public abstract class MenuItemHandler {

	/**
	 * Handles the function of the menu item.
	 * @throws VoogaException
	 */
	public abstract void handle() throws VoogaException;
}
