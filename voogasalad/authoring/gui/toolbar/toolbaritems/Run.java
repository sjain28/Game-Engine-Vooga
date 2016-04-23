package authoring.gui.toolbar.toolbaritems;

import authoring.UIManager;
import authoring.gui.toolbar.ToolbarItemHandler;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;
import resources.VoogaBundles;


public class Run extends ToolbarItemHandler {
	Save save;
	private UIManager model;

	public Run (Menuable model) {
		//save = new Save(model);
		this.model = (UIManager) model;
	}

	@Override
	public void handle () {
		GameRunner gameRunner = new GameRunner();
		//gameRunner.testLevel("games/" + VoogaBundles.preferences.getProperty("GameName") + "/levels/" + this.model.getManager().getName() + ".xml");
		gameRunner.playGame(VoogaBundles.preferences.getProperty("GameName"));
	}
}