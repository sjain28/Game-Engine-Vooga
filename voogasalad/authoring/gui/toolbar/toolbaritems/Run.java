package authoring.gui.toolbar.toolbaritems;

import java.io.FileNotFoundException;
import java.io.IOException;
import authoring.gui.toolbar.ToolbarItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;
import tools.VoogaAlert;


public class Run extends ToolbarItemHandler {
	Save save;

	public Run (Menuable model) {
		//save = new Save(model);
	}

	@Override
	public void handle () {
		GameRunner gameRunner = new GameRunner();
		gameRunner.playLevel("levels/Test.xml", true);
	}
}
