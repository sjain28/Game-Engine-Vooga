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

	public final static String GLOBAL_PROPERTIES = "Global Properties";
	private Elementable myElementable;
	
	/**
	 * Takes constructor of super class.
	 * Sets the title of the tab to be global properties.
	 */
	
	public GlobalPropertiesTab() {
		super();
		this.setText(GLOBAL_PROPERTIES);
	}

	/**
	 * Get the properties map. Can be modified to take in any object in 
	 * case of elementable or just a regular map.
	 */
	//Pass in 
	@Override
	public void getPropertiesMap(Object o) {
		propertiesMap = (Map<String, VoogaData>) o;
		displayProperties();
	}

	/**
	 * Add new property.
	 */
	@Override
	public void addNewProperty(String s, VoogaData vgData) {
		System.out.println("HERE WE GO ADDING PROPERTIES");
		myElementable.addProperty(s, vgData);
		GameObject object = (GameObject) myElementable; 
		for (String property:propertiesMap.keySet()){
	        System.out.println(property+" "+propertiesMap.get(property));
	    }
		propertiesMap.put(s, vgData);
		object.setProperties(propertiesMap);
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
	
	/**
	 * Set the map based on a previous map. 
	 * @param map
	 */
	public void setPropertiesMap(Map<String, VoogaData> map) {
		propertiesMap.clear();
		propertiesMap.putAll(map);
		displayProperties();
	}

}
