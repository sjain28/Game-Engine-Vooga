package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class NewGameObjectFileItem extends MenuItemHandler {
        EditElementable myManager;
        /**
         * Initializes the MenuItem
         * 
         * @param model to interface backend interactions with the model
         * @param event: Unused vestige of previous poor programming. Should soon be phased out.
         */
	public NewGameObjectFileItem(Menuable model, EventHandler<InputEvent> event) {
		super();
		myManager = (EditElementable) model;
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
	    Stage popup = new Stage();
	    popup.setTitle("New Game Object");
	    GameObjectBuilder initializer = new GameObjectBuilder(myManager, popup);
	    Scene scene = new VoogaScene(initializer);
	    popup.setScene(scene);
	    popup.show();
	    
	}

}
