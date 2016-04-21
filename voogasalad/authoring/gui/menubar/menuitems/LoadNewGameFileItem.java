package authoring.gui.menubar.menuitems;

import java.io.File;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.PlayerMenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
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
	
	//TODO: Change GameRunner to IGameRunner
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
		/*
		 * Using FileChooser, opens a new file and plays that file
		 * 
		 */
		this.myGameRunner.getGameDisplay().getStage().close();
//		this.myGameRunner.playGame(new File(myFileChooser.launch()));
		this.myGameRunner.playGame(myFileChooser.launch());
		this.myGameRunner.getGameDisplay().getStage().show();
	}
}
