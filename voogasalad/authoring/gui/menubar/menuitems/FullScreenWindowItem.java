package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.PlayerMenuItemHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;

/**
 * Menu Item to select the board of the display
 * 
 * @author Nick, Hunter
 *
 */
public class FullScreenWindowItem extends PlayerMenuItemHandler {
	
	private static final String EXIT_MESSAGE = "Press Ctrl + Z to exit full screen mode";
	
	private GameRunner myGameRunner;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public FullScreenWindowItem(Menuable model) {
		super();
		this.myGameRunner = (GameRunner) model;
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		/*
		 * Shuts the stage and sets fullscreen directives and defines
		 * a KeyCombination and shows the stage
		 * 
		 */
		this.myGameRunner.getGameDisplay().getStage().close();
		this.myGameRunner.getGameDisplay().getStage().setFullScreen(true);
		this.myGameRunner.getGameDisplay().getStage().setFullScreenExitHint(EXIT_MESSAGE);
		this.myGameRunner.getGameDisplay().getStage()
					.setFullScreenExitKeyCombination
					(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
		this.myGameRunner.getGameDisplay().getStage().show();
	}

}
