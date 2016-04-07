package authoring.gui.menubar;

import authoring.interfaces.model.CompleteAuthoringModelable;

public abstract class MenuItemHandler {
    private CompleteAuthoringModelable elementManager;
	
	public MenuItemHandler(CompleteAuthoringModelable model) {
	    elementManager = model;
	}
	
	public CompleteAuthoringModelable getManager(){
	    return elementManager;
	}
	public abstract void handle();
}
