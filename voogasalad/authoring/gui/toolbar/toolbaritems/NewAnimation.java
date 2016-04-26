package authoring.gui.toolbar.toolbaritems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.menuitems.NewAnimationNewItem;
import authoring.gui.toolbar.ToolbarItemHandler;

import player.gamedisplay.Menuable;
import tools.VoogaException;

public class NewAnimation extends ToolbarItemHandler{
	
	MenuItemHandler animationCreator;
    
    public NewAnimation(Menuable model){
        animationCreator = new NewAnimationNewItem(model);
    }
    @Override
    public void handle () {
        try {
			animationCreator.handle();
		} catch (VoogaException e) {
			e.printStackTrace();
		}
    }
 
}
