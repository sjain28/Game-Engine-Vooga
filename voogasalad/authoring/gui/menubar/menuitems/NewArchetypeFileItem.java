package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.ArchetypeBuilder;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class NewArchetypeFileItem extends MenuItemHandler{
    EditElementable myManager;
    
    public NewArchetypeFileItem(CompleteAuthoringModelable model, EventHandler<InputEvent> event) {
            super();
            myManager = model;
    }


    @Override
    public void handle () {
        Stage popup = new Stage();
        popup.setTitle("New Archetype");
        ArchetypeBuilder initializer = new ArchetypeBuilder(myManager, popup);
        Scene scene = new VoogaScene(initializer);
        popup.setScene(scene);
        popup.show();
    }

}
