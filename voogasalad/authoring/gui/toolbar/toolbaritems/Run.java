package authoring.gui.toolbar.toolbaritems;

import authoring.UIManager;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;
import resources.VoogaBundles;


public class Run extends ToolbarItemHandler {
    Save save;
    private UIManager model;
    private String game;

	public Run (Menuable model) {
		this.model = (UIManager) model;
		game = VoogaBundles.preferences.getProperty("GameName");
	}

	@Override
	public void handle () {
		GameRunner gameRunner = new GameRunner();
		gameRunner.playGame(game);
	}
}

