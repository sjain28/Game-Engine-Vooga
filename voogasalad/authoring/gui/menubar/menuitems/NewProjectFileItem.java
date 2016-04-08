package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Savable;

public class NewProjectFileItem extends MenuItemHandler {
    private Savable myManager;
	
	public NewProjectFileItem(CompleteAuthoringModelable model) {
		super();
		myManager = model;
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		
	}

}
