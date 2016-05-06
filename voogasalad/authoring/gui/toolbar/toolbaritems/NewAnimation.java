package authoring.gui.toolbar.toolbaritems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.menuitems.NewAnimationNewItem;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;
import tools.VoogaException;

public class NewAnimation extends ToolbarItemHandler{
	
	MenuItemHandler animationCreator;
    
	
    /**
     * Initializes button takes in the backend interface
     * 
     * @param model
     */
    public NewAnimation (Menuable model) {
        animationCreator = new NewAnimationNewItem(model);
    }

    /**
     * Defines what to do when the button is clicked
     */
    @Override
    public void handle () {
        try {
            animationCreator.handle();
        }
        catch (VoogaException e) {
        }
    }

}
