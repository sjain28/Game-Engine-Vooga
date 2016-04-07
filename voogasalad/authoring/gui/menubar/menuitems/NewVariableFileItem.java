package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;

public class NewVariableFileItem extends MenuItemHandler {
        private EditElementable myManager;
	
	public NewVariableFileItem(CompleteAuthoringModelable model) {
		super();
		myManager = model;
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		
	}

}
