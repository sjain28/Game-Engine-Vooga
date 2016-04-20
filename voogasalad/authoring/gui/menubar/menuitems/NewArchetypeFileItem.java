package authoring.gui.menubar.menuitems;

import authoring.Command;
import authoring.VoogaScene;
import authoring.gui.menubar.AuthoringMenuItemHandler;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.ArchetypeBuilder;
import authoring.gui.menubar.builders.Builder;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class NewArchetypeFileItem extends AuthoringMenuItemHandler {
    EditElementable myManager;
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
    public NewArchetypeFileItem(Menuable model, Command event) {
            super();
            myManager = (EditElementable) model;
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
