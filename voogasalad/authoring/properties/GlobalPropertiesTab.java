package authoring.properties;

/**
 * Tab to represent global properties/variables.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 * 
 */

import java.util.Map;

import authoring.interfaces.Elementable;
import authoring.model.GameObject;
import authoring.model.GlobalPropertiesManager;
import tools.interfaces.VoogaData;

public class GlobalPropertiesTab extends AbstractPropertiesTab {

	public final static String GLOBAL_PROPERTIES = "Level Variables";
	private Elementable myElementable;
	
	/**
	 * Takes constructor of super class.
	 * Sets the title of the tab to be global properties.
	 */
	
	public GlobalPropertiesTab() {
		super();
		this.setText(GLOBAL_PROPERTIES);
	}

	public void getMap(Map<String, VoogaData> global) {
		propertiesMap.putAll(global);
	}
}
