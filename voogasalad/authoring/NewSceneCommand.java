package authoring;

import java.util.List;

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import authoring.model.ElementTabManager;

public class NewSceneCommand implements Command {
	
	private ElementTabManager tabManager;
	private UIGrid grid;
	
	public NewSceneCommand(ElementTabManager tabManager, UIGrid grid) {
		this.tabManager = tabManager;
		this.grid = grid;
	}

	@Override
	public void execute() {
		tabManager.addManager(new ElementManager());
		grid.addScene(tabManager.getCurrentManager());
	}

}
