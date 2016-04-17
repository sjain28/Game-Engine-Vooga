package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.ImportArchetype;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import player.gamedisplay.Menuable;
import tools.VoogaException;

public class NewImportArchetypeFileItem extends MenuItemHandler{
    private CompleteAuthoringModelable manager;
    private EventHandler event;
    public NewImportArchetypeFileItem(Menuable model, EventHandler event){
        this.setManager((CompleteAuthoringModelable) model);
        this.event=event;
    }
    @Override
    public void handle () throws VoogaException {
        ImportArchetype importArchetype = new ImportArchetype(getManager());
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
