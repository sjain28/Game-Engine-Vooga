package authoring.gui.toolbar.toolbaritems;

import authoring.gui.menubar.menuitems.OpenLevelFileItem;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;
import tools.VoogaAlert;
import tools.VoogaException;

public class Load extends ToolbarItemHandler{
    OpenLevelFileItem openLevel;
    
    public Load(Menuable model){
    	openLevel = new OpenLevelFileItem(model);
    }
    
    @Override
    public void handle () {
        try {
            openLevel.handle();
        }
        catch (VoogaException e) {
            VoogaAlert alert = new VoogaAlert(e.getMessage());
            alert.showAndWait();
        }
        
    }

}
