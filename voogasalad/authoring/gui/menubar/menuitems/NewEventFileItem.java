package authoring.gui.menubar.menuitems;

import authoring.gui.eventpane.EventWindow;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditEventable;
import javafx.event.EventHandler;

public class NewEventFileItem extends MenuItemHandler{
    private EditEventable myManager;
    private EventWindow eventWindow;
    
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
    public NewEventFileItem(CompleteAuthoringModelable model, EventHandler event){
        super();
        myManager = model;
        eventWindow = new EventWindow(myManager);
    }
    /**
     * Action to be taken on the selection of this menuItem
     */
    @Override
    public void handle() {
        EventWindow eventWindow = new EventWindow(myManager);
        eventWindow.show();
        
    }


}