package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.TextObjectBuilder;
import authoring.gui.menubar.builders.VariableBuilder;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class NewVariableFileItem extends MenuItemHandler {
        private EditElementable myManager;
        /**
         * Initializes the MenuItem
         * 
         * @param model to interface backend interactions with the model
         * @param event: Unused vestige of previous poor programming. Should soon be phased out.
         */
	public NewVariableFileItem(CompleteAuthoringModelable model, EventHandler<InputEvent> event) {
		super();
		myManager = model;
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
	    Stage popup = new Stage();
            popup.setTitle("New Variable");
            VariableBuilder initializer = new VariableBuilder(myManager, popup);
            Scene scene = new VoogaScene(initializer);
            popup.setScene(scene);
            popup.show();
            
	}

}
