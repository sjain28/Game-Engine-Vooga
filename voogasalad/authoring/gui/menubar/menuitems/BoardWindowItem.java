package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;


/**
 * Menu Item to select the board of the display
 * 
 * @author Nick
 *
 */
public class BoardWindowItem extends MenuItemHandler {
	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public BoardWindowItem(CompleteAuthoringModelable model, EventHandler<InputEvent> event) {
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		// TODO Auto-generated method stub
		
	}

}
