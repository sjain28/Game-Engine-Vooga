package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.ImportArchetype;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import tools.VoogaException;

public class NewImportArchetypeFileItem extends MenuItemHandler{
    CompleteAuthoringModelable manager;
    EventHandler event;
    public NewImportArchetypeFileItem(CompleteAuthoringModelable model, EventHandler event){
        this.manager=model;
        this.event=event;
    }
    @Override
    public void handle () throws VoogaException {
        ImportArchetype importArchetype = new ImportArchetype(manager);
        importArchetype.show();
        
    }

}
