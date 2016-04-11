package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.GameObjectBuilder;
import authoring.gui.eventpane.EventWindow;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditEventable;
import authoring.interfaces.model.Savable;
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
        Stage popup = new Stage();
        popup.setTitle("New Event");
        Scene scene = new VoogaScene(eventWindow);
        popup.setScene(scene);
        popup.show();
        
    }


}