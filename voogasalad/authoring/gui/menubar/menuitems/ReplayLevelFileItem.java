package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.PlayerMenuItemHandler;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;

/**
 * Menu Item to select the board of the display
 * 
 * @author Nick, Hunter
 *
 */

public class ReplayLevelFileItem extends PlayerMenuItemHandler {

	private GameRunner myGameRunner;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public ReplayLevelFileItem(Menuable model) {

		super();
		this.myGameRunner = (GameRunner) model;
	}

	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		myGameRunner.replayLevel();
	}
}
