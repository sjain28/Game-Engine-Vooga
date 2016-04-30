package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.PlayerMenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;
import player.gamerunner.IGameRunner;


/**
 * Menu Item to select the board of the display
 * 
 * @author Nick, Hunter
 *
 */
public class SaveGameFileItem extends PlayerMenuItemHandler {
    private GameRunner myGameRunner;
	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */

	public SaveGameFileItem(Menuable model) {
		super();
		this.myGameRunner = (GameRunner) model;
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		
		//Save Game Progress saves this as the name of the player who is currently playing.
		myGameRunner.saveGameProgress();
		System.out.println("This save game file class was accessed");
	}

}
