package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Savable;

public class OpenProjectFileItem extends MenuItemHandler {
    private Savable myManager;
	
	public OpenProjectFileItem(CompleteAuthoringModelable model) {
		super();
		myManager = model;
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		
	}

}
