package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.builders.ImportArchetype;
import authoring.gui.menubar.builders.SaveArchetype;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;

public class SaveArchetypeFileItem extends NewImportArchetypeFileItem{

    public SaveArchetypeFileItem (CompleteAuthoringModelable model, EventHandler event) {
        super(model, event);
    }
    
    @Override
    public void handle(){
        SaveArchetype saveArchetype = new SaveArchetype(manager);
        saveArchetype.show();
    }
}
