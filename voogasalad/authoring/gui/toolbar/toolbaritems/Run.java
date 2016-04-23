package authoring.gui.toolbar.toolbaritems;

import java.io.FileNotFoundException;
import java.io.IOException;

import authoring.UIManager;
import authoring.gui.toolbar.ToolbarItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;
import resources.VoogaBundles;
import tools.VoogaAlert;


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
		gameRunner.testLevel("games/My DoovalSalad Project/levels/Test.xml");
	}
}
