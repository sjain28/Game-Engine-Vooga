package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.model.ElementManagerUnserializer;
import javafx.stage.FileChooser.ExtensionFilter;
import player.gamedisplay.Menuable;
import tools.VoogaException;
import tools.VoogaFileChooser;


public class OpenLevelFileItem extends MenuItemHandler {

    private Menuable myUIManager;

    public OpenLevelFileItem (Menuable model) {
        super();
        myUIManager = model;
    }

    @Override
    public void handle () throws VoogaException {
        String xmlPath= getLevelPath();
        ElementManagerUnserializer unserializer = new ElementManagerUnserializer(xmlPath);
        myUIManager.addScene(unserializer.unserialize());
    }
    
    private String getLevelPath() throws VoogaException{
        VoogaFileChooser fileChooser = new VoogaFileChooser();
        fileChooser.addFilters(new ExtensionFilter("XML (.xml)","*.xml"));
        return fileChooser.launch();
    }

}
