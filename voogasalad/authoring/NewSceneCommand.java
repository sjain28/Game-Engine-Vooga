package authoring;

/**
 * Command that handles the creation of new scene/workspace.
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 * 
 */

import authoring.model.ElementManager;
import authoring.model.ElementTabManager;

public class NewSceneCommand implements Command {

	private ElementTabManager tabManager;
	private UIGrid grid;

	/**
	 * Constructor to populate the UI grid and give the scene it's own Element Tab Manager.
	 * 
	 * @param tabManager
	 * @param grid
	 */
	public NewSceneCommand(ElementTabManager tabManager, UIGrid grid) {
		this.tabManager = tabManager;
		this.grid = grid;
	}

	/**
	 * On execution, adds new element manager to element tab manager and populates the scene.
	 */
	@Override
	public void execute() {
		tabManager.addManager(new ElementManager());
		grid.addScene(tabManager.getCurrentManager());
	}
}
