package authoring.gui.toolbar.toolbaritems;

import authoring.gui.eventpane.EventWindow;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;

public class NewEvent extends ToolbarItemHandler{
    private EventWindow gameObjectCreator;
    
    
    /**
     * Initializes button takes in the backend interface
     * @param model
     */
    public NewEvent(Menuable model){
        gameObjectCreator = new EventWindow(model.getManager());
    }
    
    
    /**
     * Defines what to do when the button is clicked
     */
    @Override
    public void handle () {
        gameObjectCreator.show();
    }

}
