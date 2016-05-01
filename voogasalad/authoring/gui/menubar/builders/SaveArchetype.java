package authoring.gui.menubar.builders;


import authoring.interfaces.model.EditSpritable;
import tools.VoogaAlert;
import tools.VoogaException;

/**
 * Class to help save archetypes through importation.
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */
public class SaveArchetype extends ImportArchetype{

	/**
	 * Initialization and connects to manager.
	 * @param manager
	 */
	public SaveArchetype (EditSpritable manager) {
		super();
		setManager(manager);
		addArchetypeNames(manager.getSpriteFactory().getAllArchetypeNames());
	}

	/**
	 * Apply and save the archetype.
	 */
	protected void apply(){
		try {
			for (String name : getSelectedArchetypes()){
				getManager().getSpriteFactory().saveArchetype(name);
			}
			this.close();
		}
		catch (VoogaException e) {
			VoogaAlert alert = new VoogaAlert(e.getMessage());
			alert.showAndWait();
		}
	}
}
