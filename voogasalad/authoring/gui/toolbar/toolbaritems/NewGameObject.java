package authoring.gui.toolbar.toolbaritems;

import authoring.gui.menubar.menuitems.NewGameObjectNewItem;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;

public class NewGameObject extends ToolbarItemHandler{
    private NewGameObjectNewItem gameObjectCreator;
    
    
    /**
     * Initializes button takes in the backend interface
     * @param model
     */
    public NewGameObject(Menuable model){
        gameObjectCreator = new NewGameObjectNewItem(model);
    }
    
    /**
     * Defines what to do when the button is clicked
     */
    @Override
    public void handle () {
        gameObjectCreator.handle();
    }

}
