package authoring.gui.toolbar.toolbaritems;

import authoring.gui.menubar.menuitems.NewArchetypeNewItem;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;

public class NewArchetype extends ToolbarItemHandler{
    private NewArchetypeNewItem archetypeCreator;
    
    public NewArchetype(Menuable model){
        archetypeCreator = new NewArchetypeNewItem(model);
    }
    @Override
    public void handle () {
        archetypeCreator.handle();
    }
 
}
