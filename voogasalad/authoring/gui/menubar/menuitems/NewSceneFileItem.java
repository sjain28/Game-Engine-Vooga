package authoring.gui.menubar.menuitems;

import authoring.Command;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import player.gamedisplay.Menuable;

public class NewSceneFileItem extends MenuItemHandler {
        private Command myEvent;
        /**
         * Initializes the MenuItem
         * 
         * @param model to interface backend interactions with the model
         * @param event: Unused vestige of previous poor programming. Should soon be phased out.
         */
	public NewSceneFileItem(Menuable model, Command event) {
		super();
		myEvent = event;
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
		myEvent.execute();
	}

}
