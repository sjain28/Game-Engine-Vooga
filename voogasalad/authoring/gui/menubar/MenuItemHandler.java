package authoring.gui.menubar;

import authoring.interfaces.model.CompleteAuthoringModelable;
import auxiliary.VoogaException;

public abstract class MenuItemHandler {
 
	public MenuItemHandler() {
	}
	
	public abstract void handle() throws VoogaException;
}
