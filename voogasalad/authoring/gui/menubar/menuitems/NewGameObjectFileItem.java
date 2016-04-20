package authoring.gui.menubar.menuitems;

import authoring.Command;
import authoring.VoogaScene;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.Builder;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.EditElementable;
import authoring.model.ElementTabManager;
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
	public NewGameObjectFileItem(Menuable model, Command event) {
		super();
		myManager = (EditElementable) model;
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
	    Builder popup = new GameObjectBuilder(myManager);
	    popup.setTitle("New Game Object");
	    popup.show();
	}

}
