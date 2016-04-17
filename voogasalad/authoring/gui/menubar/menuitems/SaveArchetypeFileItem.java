package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.builders.SaveArchetype;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;

public class SaveArchetypeFileItem extends NewImportArchetypeFileItem{

    public SaveArchetypeFileItem (Menuable model, EventHandler<InputEvent> event) {
        super(model, event);
    }
    
    @Override
    public void handle(){
        SaveArchetype saveArchetype = new SaveArchetype(manager);
        saveArchetype.show();
    }
}
