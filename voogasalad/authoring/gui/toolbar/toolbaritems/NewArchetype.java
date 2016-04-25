package authoring.gui.toolbar.toolbaritems;

import authoring.gui.menubar.menuitems.NewArchetypeFileItem;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;

public class NewArchetype extends ToolbarItemHandler{
    private NewArchetypeFileItem archetypeCreator;
    
    public NewArchetype(Menuable model){
        archetypeCreator = new NewArchetypeFileItem(model);
    }
    @Override
    public void handle () {
        archetypeCreator.handle();
    }
 
}
