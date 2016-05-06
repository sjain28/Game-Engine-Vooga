package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.PlayerMenuItemHandler;
import player.gamedisplay.Menuable;
import resources.VoogaBundles;
import stats.interaction.CurrentSessionStats;


/**
 * Menu Item to select the board of the display
 * 
 * @author Nick, Hunter
 *
 */

public class SaveGameFileItem extends PlayerMenuItemHandler {

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public SaveGameFileItem(Menuable model) {
		super();
	}

	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		CurrentSessionStats stats = new CurrentSessionStats();
		stats.saveGameProgress(VoogaBundles.preferences.getProperty("UserName"));
	}

}
