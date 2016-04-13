package authoring.gui.toolbar.toolbaritems;

import authoring.gui.toolbar.ToolbarItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import player.gamedisplay.GameDisplayTester;

public class Run extends ToolbarItemHandler {

	public Run(CompleteAuthoringModelable model) {
	}

	@Override
	public void handle() {
		new GameDisplayTester();
	}

}
