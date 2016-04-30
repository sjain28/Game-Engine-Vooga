package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.AuthoringMenuItemHandler;
import authoring.gui.menubar.builders.ArchetypeBuilder;
import authoring.gui.menubar.builders.Builder;
import authoring.interfaces.model.EditElementable;
import player.gamedisplay.Menuable;

public class NewArchetypeNewItem extends AuthoringMenuItemHandler {
    EditElementable myManager;
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
    public NewArchetypeNewItem(Menuable model) {

            super();
            myManager =  (EditElementable) model.getManager();
    }

    /**
     * Action to be taken on the selection of this menuItem
     */
    @Override
    public void handle () {
        Builder initializer = new ArchetypeBuilder(myManager);
        initializer.setTitle("Create an Archetype");
        initializer.show();
    }

}
