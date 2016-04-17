package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.builders.ImportArchetype;
import authoring.gui.menubar.builders.SaveArchetype;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import player.gamedisplay.Menuable;

public class SaveArchetypeFileItem extends NewImportArchetypeFileItem{

    public SaveArchetypeFileItem (Menuable model, EventHandler event) {
        super(model, event);
    }
    
    @Override
    public void handle(){
        SaveArchetype saveArchetype = new SaveArchetype(getManager());
        saveArchetype.show();
    }
}
