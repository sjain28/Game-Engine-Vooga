package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.eventpane.EventWindow;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditEventable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class NewEventFileItem extends MenuItemHandler{
    private EditEventable myManager;
    
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
    public NewEventFileItem(Menuable model, EventHandler<InputEvent> event){
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