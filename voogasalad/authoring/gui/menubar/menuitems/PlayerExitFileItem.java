package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.PlayerMenuItemHandler;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;

/**
 * MenuItem that defines functionality to exit out of the program
 * 
 * @author Nick, Hunter
 *
 */

public class PlayerExitFileItem extends PlayerMenuItemHandler {


	private GameRunner myGameRunner;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public PlayerExitFileItem(Menuable model) {
		super();
		this.myGameRunner = (GameRunner) model;
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 * Closes the current workspace.
	 */
	@Override
	public void handle() {
		this.myGameRunner.exit();
	}

}
