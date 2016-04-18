package authoring.gui.menubar.menuitems;

import application.Launcher;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class NewProjectFileItem extends MenuItemHandler {
    private Saveable myManager;
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
	public NewProjectFileItem(Menuable model, EventHandler<InputEvent> event) {
		super();
		myManager = (Saveable) model;
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
		new Launcher(new Stage());
	}

}
