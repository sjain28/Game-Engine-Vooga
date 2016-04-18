package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.PlayerMenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;


/**
 * Menu Item to select the board of the display
 * 
 * @author Nick
 *
 */
public class LoadNewGameFileItem extends PlayerMenuItemHandler {
	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public LoadNewGameFileItem(Menuable model, EventHandler<InputEvent> event) {
		super();
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		// TODO Auto-generated method stub
		
	}

}