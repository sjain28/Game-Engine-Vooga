package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.AuthoringMenuItemHandler;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.ImportArchetype;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;
import tools.VoogaException;

public class NewImportArchetypeFileItem extends AuthoringMenuItemHandler {
    CompleteAuthoringModelable manager;
    
    public NewImportArchetypeFileItem(Menuable model, Sceneable uiModel){
        this.manager = (CompleteAuthoringModelable) model;
    }
    @Override
    public void handle () throws VoogaException {
        ImportArchetype importArchetype = new ImportArchetype(manager);
        importArchetype.show();
    }
    /**
     * @return the manager
     */
    protected CompleteAuthoringModelable getManager () {
        return manager;
    }
    /**
     * @param manager the manager to set
     */
    private void setManager (CompleteAuthoringModelable manager) {
        this.manager = manager;
    }

}
