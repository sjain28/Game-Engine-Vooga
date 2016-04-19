package authoring.gui.menubar.menuitems;

import authoring.gui.eventpane.EventWindow;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.EditEventable;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;

public class NewEventFileItem extends MenuItemHandler{
    private EditEventable myManager;
    
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
    public NewEventFileItem(Menuable model, Sceneable uiModel){
        super();
        myManager = (EditEventable) model;
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