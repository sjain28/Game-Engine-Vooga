package authoring.gui.toolbar.toolbaritems;

import authoring.gui.menubar.menuitems.NewGameObjectFileItem;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;

public class NewGameObject extends ToolbarItemHandler{
    private NewGameObjectFileItem gameObjectCreator;
    
    public NewGameObject(Menuable model){
        gameObjectCreator = new NewGameObjectFileItem(model);
    }
    @Override
    public void handle () {
        gameObjectCreator.handle();
    }

}
