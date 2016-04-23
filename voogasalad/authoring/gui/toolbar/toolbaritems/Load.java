package authoring.gui.toolbar.toolbaritems;

import java.io.File;
import authoring.gui.toolbar.ToolbarItemHandler;
import javafx.stage.FileChooser.ExtensionFilter;
import player.gamedisplay.Menuable;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;
import tools.VoogaFileChooser;

public class Load extends ToolbarItemHandler{
    Menuable manager;
    public Load(Menuable model){
    	manager = model;
    }
    
    @Override
    public void handle () {
        VoogaFileChooser fileChooser = new VoogaFileChooser();
        fileChooser.addFilters(new ExtensionFilter("XML (.xml)","*.xml"));
        fileChooser.setInitialDirectory(new File("games/"+VoogaBundles.preferences.getProperty("GameName")+"/"));
        try {
            fileChooser.launch();
        }
        catch (VoogaException e) {
            new VoogaAlert(e.getMessage());
        }
    }

}
