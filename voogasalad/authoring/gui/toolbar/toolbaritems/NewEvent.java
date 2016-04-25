package authoring.gui.toolbar.toolbaritems;

import authoring.gui.eventpane.EventWindow;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;

public class NewEvent extends ToolbarItemHandler{
    private EventWindow gameObjectCreator;
    
    public NewEvent(Menuable model){
        gameObjectCreator = new EventWindow(model.getManager());
    }
    
    @Override
    public void handle () {
        gameObjectCreator.show();
    }

}
