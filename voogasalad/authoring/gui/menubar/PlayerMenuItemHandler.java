package authoring.gui.menubar;


import tools.VoogaException;

public abstract class PlayerMenuItemHandler extends MenuItemHandler {
	
	@Override
	public abstract void handle() throws VoogaException;

}
