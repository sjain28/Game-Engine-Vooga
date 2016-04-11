package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.GameObjectBuilder;
import authoring.gui.eventpane.EventWindow;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditEventable;
import authoring.interfaces.model.Savable;
<<<<<<< HEAD
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public class NewEventFileItem extends MenuItemHandler{
    private EditEventable myManager;

    public NewEventFileItem(CompleteAuthoringModelable model, EventHandler<InputEvent> event){
=======
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewEventFileItem extends MenuItemHandler{
    private EditEventable myManager;
    private EventWindow eventWindow;
    public NewEventFileItem(CompleteAuthoringModelable model){
>>>>>>> 5e47048b33a4561e26ec365c448f946a76225f1d
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