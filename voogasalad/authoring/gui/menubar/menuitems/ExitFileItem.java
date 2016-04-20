package authoring.gui.menubar.menuitems;

import authoring.Command;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;
/**
 * MenuItem that defines functionality to exit out of the program
 * 
 * @author Nick
 *
 */
public class ExitFileItem extends MenuItemHandler {
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
	public ExitFileItem(Menuable model, Command event) {
		
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
		System.exit(0);
	}

}
