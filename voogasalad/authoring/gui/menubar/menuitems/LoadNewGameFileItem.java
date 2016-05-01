package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.PlayerMenuItemHandler;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;
import tools.VoogaException;
import tools.VoogaFileChooser;

/**
 * Menu Item to select the board of the display
 * 
 * @author Nick, Hunter
 *
 */
public class LoadNewGameFileItem extends PlayerMenuItemHandler {
	
	private GameRunner myGameRunner;
	private VoogaFileChooser myFileChooser;
	
	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */

	public LoadNewGameFileItem(Menuable model) {

		super();
		this.myGameRunner = (GameRunner) model;
		this.myFileChooser = new VoogaFileChooser();
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 * @throws VoogaException 
	 */
	@Override
	public void handle() throws VoogaException {

		this.myGameRunner.getGameDisplay().getStage().close();
		this.myGameRunner.playGame(myFileChooser.launch());
		this.myGameRunner.getGameDisplay().getStage().show();
	}
}
