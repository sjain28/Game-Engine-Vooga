package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditEventable;
import authoring.interfaces.model.Savable;

public class NewEventFileItem extends MenuItemHandler{
    private EditEventable myManager;

    public NewEventFileItem(CompleteAuthoringModelable model){
        super();
        myManager = model;
    }
    @Override
    public void handle () {
        // TODO Auto-generated method stub
        
    }

}
