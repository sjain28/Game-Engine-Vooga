package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;

public class NewProjectFileItem extends MenuItemHandler {
    private Saveable myManager;
	
	public NewProjectFileItem(CompleteAuthoringModelable model) {
		super();
		myManager = model;
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		
	}

}
