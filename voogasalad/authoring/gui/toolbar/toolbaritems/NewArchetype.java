package authoring.gui.toolbar.toolbaritems;

import authoring.gui.menubar.menuitems.NewArchetypeNewItem;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;

public class NewArchetype extends ToolbarItemHandler{
    private NewArchetypeNewItem archetypeCreator;
    
    
    /**
     * Initializes button takes in the backend interface
     * @param model
     */
    public NewArchetype(Menuable model){
        archetypeCreator = new NewArchetypeNewItem(model);
    }
    
    /**
     * Defines what to do when the button is clicked
     */
    @Override
    public void handle () {
        archetypeCreator.handle();
    }
 
}
