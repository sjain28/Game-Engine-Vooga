package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.builders.SaveArchetype;
import player.gamedisplay.Menuable;

/**
 * File Menu Item to save archetype.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class SaveArchetypeFileItem extends NewImportArchetypeFileItem{

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
    public SaveArchetypeFileItem (Menuable model) {
        super(model);

    }
    
    /**
	 * Action to be taken on the selection of this menuItem
	 */
    @Override
    public void handle(){
        SaveArchetype saveArchetype = new SaveArchetype(manager);
        saveArchetype.show();
    }
}
