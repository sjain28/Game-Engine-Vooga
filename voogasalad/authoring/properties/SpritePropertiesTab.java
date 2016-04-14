package authoring.properties;

/**
 * Tab to represent sprite properties/variables.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 * 
 */

import authoring.interfaces.Elementable;
import tools.interfaces.VoogaData;

public class SpritePropertiesTab extends AbstractPropertiesTab {
	
	public final static String SPRITE_PROPERTIES = "Sprite Properties";
	private Elementable myElementable;
	
	/**
	 * Takes constructor of super class.
	 * Sets the title of the tab to be sprite properties.
	 */

	public SpritePropertiesTab() {
		super();
		this.setText(SPRITE_PROPERTIES);
	}

	/**
	 * Get the properties map. Can be modified to take in any object in 
	 * case of elementable or just a regular map.
	 */
	@Override
	public void getPropertiesMap(Object o) {
		myElementable = (Elementable) o;
		propertiesMap = myElementable.getVoogaProperties();
		originalPropertiesMap = myElementable.getVoogaProperties();
		displayProperties();
	}

	/**
	 * Add new property.
	 */
	@Override
	public void addNewProperty(String s, VoogaData vgData) {
		myElementable.addProperty(s, vgData);
		displayProperties();
	}

	/**
	 * Removes a property.
	 */
	@Override
	public void removeProperty(String s) {
		myElementable.removeProperty(s);
		displayProperties();
	}

}
