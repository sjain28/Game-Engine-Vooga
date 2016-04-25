package authoring.gui.menubar.builders;


import authoring.interfaces.model.EditSpritable;
import tools.VoogaAlert;
import tools.VoogaException;

public class SaveArchetype extends ImportArchetype{
	public SaveArchetype (EditSpritable manager) {
		super();
		setManager(manager);
		addArchetypeNames(manager.getSpriteFactory().getAllArchetypeNames());
	}

	protected void apply(){
		try {
			for (String name : getSelectedArchetypes()){
				getManager().getSpriteFactory().saveArchetype(name);
			}
			this.close();
		}
		catch (VoogaException e) {
			new VoogaAlert(e.getMessage());
		}
	}
}
