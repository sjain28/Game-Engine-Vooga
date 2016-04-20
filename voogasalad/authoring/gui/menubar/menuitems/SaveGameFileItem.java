package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.PlayerMenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;


/**
 * Menu Item to select the board of the display
 * 
 * @author Nick
 *
 */
public class SaveGameFileItem extends PlayerMenuItemHandler {
    private Menuable myModel;
	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */

	public SaveGameFileItem(Menuable model) {
		super();
		myModel = model;
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		myModel.saveAll();
	}

}
