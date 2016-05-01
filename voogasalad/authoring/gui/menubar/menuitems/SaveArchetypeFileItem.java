package authoring.gui.menubar.menuitems;


import authoring.gui.menubar.builders.SaveArchetype;
import player.gamedisplay.Menuable;

public class SaveArchetypeFileItem extends NewImportArchetypeFileItem{

    public SaveArchetypeFileItem (Menuable model) {
        super(model);

    }
    
    @Override
    public void handle(){
        SaveArchetype saveArchetype = new SaveArchetype(manager);
        saveArchetype.show();
    }
}
