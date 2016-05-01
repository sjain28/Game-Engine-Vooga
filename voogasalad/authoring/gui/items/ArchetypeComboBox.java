package authoring.gui.items;

import authoring.interfaces.model.EditEventable;

/**
 * ComboBox that contains all available types of archetypes to choose from.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public class ArchetypeComboBox extends SpriteComboBox {

	/**
	 * Takes constructor of super class.
	 * @param manager: to extract information from
	 */
	public ArchetypeComboBox (EditEventable manager) {
		super(manager);
	}

	/**
	 * Load data of archetypes.
	 */
	protected void loadData () {
		getItems().addAll(getManager().getSpriteFactory().getAllArchetypeNames());
	}

	/**
	 * Get Sprite ID of the selected sprite.
	 */
	public String getSpriteId () {
		return this.getValue();
	}

}
