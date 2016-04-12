package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.GameObjectBuilder;
import authoring.gui.eventpane.EventWindow;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditEventable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewEventFileItem extends MenuItemHandler{
    private EditEventable myManager;
    private EventWindow eventWindow;
    public NewEventFileItem(CompleteAuthoringModelable model){
        super();
        myManager = model;
        eventWindow = new EventWindow(myManager);
    }
    
    @Override
    public void handle() {
        EventWindow eventWindow = new EventWindow(myManager);
        eventWindow.show();
        
    }


}