package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import player.gamedisplay.Menuable;

public class NewSceneNewItem extends MenuItemHandler {

    Menuable myUIManager;
        /**
         * Initializes the MenuItem
         * 
         * @param model to interface backend interactions with the model
         * @param event: Unused vestige of previous poor programming. Should soon be phased out.
         */
	public NewSceneNewItem(Menuable model) {

		super();
		myUIManager = model;
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
		myUIManager.addScene();
	}

}