package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.AuthoringMenuItemHandler;
import authoring.gui.menubar.builders.ImportArchetype;
import authoring.interfaces.model.CompleteAuthoringModelable;
import player.gamedisplay.Menuable;
import tools.VoogaException;

public class NewImportArchetypeFileItem extends AuthoringMenuItemHandler {
    CompleteAuthoringModelable manager;
    
    public NewImportArchetypeFileItem(Menuable model){
        this.manager = (CompleteAuthoringModelable) model.getManager();
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
