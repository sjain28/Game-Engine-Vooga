package authoring.gui.toolbar.toolbaritems;

import java.io.File;
import authoring.gui.menubar.menuitems.OpenLevelFileItem;
import authoring.gui.toolbar.ToolbarItemHandler;
import javafx.stage.FileChooser.ExtensionFilter;
import player.gamedisplay.Menuable;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;
import tools.VoogaFileChooser;

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
            new VoogaAlert(e.getMessage());
        }
        
    }

}
