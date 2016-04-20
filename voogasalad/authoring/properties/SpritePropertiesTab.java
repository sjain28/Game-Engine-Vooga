package authoring.properties;

import java.util.HashMap;

import authoring.gui.menubar.builders.PropertyBuilder;

/**
 * Tab to represent sprite properties/variables.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 * 
 */

import authoring.interfaces.Elementable;
import authoring.model.GameObject;
import javafx.beans.property.SimpleMapProperty;
import tools.interfaces.VoogaData;

public class SpritePropertiesTab extends AbstractPropertiesTab {
	
	public final static String SPRITE_PROPERTIES = "Sprite Properties";
	
	/**
	 * Takes constructor of super class.
	 * Sets the title of the tab to be sprite properties.
	 */

	public SpritePropertiesTab() {
		super();
		this.setText(SPRITE_PROPERTIES);
	}

}
