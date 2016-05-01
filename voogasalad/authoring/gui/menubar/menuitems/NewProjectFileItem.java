package authoring.gui.menubar.menuitems;

import authoring.UILauncher;
import authoring.gui.menubar.MenuItemHandler;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class NewProjectFileItem extends MenuItemHandler {
    //private Saveable myManager;
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
	public NewProjectFileItem(Menuable model) {
		super();
		//myManager = (Saveable) model.getManager();
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
		new UILauncher(new Stage());
	}

}
