package authoring.gui.toolbar.toolbaritems;

import authoring.gui.menubar.menuitems.NewGameObjectNewItem;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;

public class NewGameObject extends ToolbarItemHandler{
    private NewGameObjectNewItem gameObjectCreator;
    
    public NewGameObject(Menuable model){
        gameObjectCreator = new NewGameObjectNewItem(model);
    }
    @Override
    public void handle () {
        gameObjectCreator.handle();
    }

}
