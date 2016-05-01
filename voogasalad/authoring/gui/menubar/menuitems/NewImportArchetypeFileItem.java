package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.AuthoringMenuItemHandler;
import authoring.gui.menubar.builders.ImportArchetype;
import authoring.interfaces.model.CompleteAuthoringModelable;
import player.gamedisplay.Menuable;
import tools.VoogaException;

/**
 * File Menu Item to import an archetype.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class NewImportArchetypeFileItem extends AuthoringMenuItemHandler {

	CompleteAuthoringModelable manager;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public NewImportArchetypeFileItem(Menuable model){
		this.manager = (CompleteAuthoringModelable) model.getManager();
	}

	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle () throws VoogaException {
		ImportArchetype importArchetype = new ImportArchetype(manager);
		importArchetype.show();
	}
	/**
	 * @return the manager
	 */
	protected CompleteAuthoringModelable getManager () {
		return manager;
	}
	/**
	 * @param manager the manager to set
	 */
	private void setManager (CompleteAuthoringModelable manager) {
		this.manager = manager;
	}

}
