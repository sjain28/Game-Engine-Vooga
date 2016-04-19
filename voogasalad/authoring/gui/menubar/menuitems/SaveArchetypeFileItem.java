package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.builders.SaveArchetype;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;

public class SaveArchetypeFileItem extends NewImportArchetypeFileItem{

    public SaveArchetypeFileItem (Menuable model, Sceneable uiModel) {
        super(model, uiModel);
    }
    
    @Override
    public void handle(){
        SaveArchetype saveArchetype = new SaveArchetype(manager);
        saveArchetype.show();
    }
}
