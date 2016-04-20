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
	public void getPropertiesMap(Elementable elem) {
		myElementable = elem;
		propertiesMap = myElementable.getVoogaProperties();
		displayProperties();
	}

	/**
	 * Add new property.
	 */
	@Override
	public void addNewProperty(String s, VoogaData vgData) {
		
//		PropertyBuilder pBuilder = new PropertyBuilder();
//      pBuilder.showAndWait();
//      myElementable.addProperty(pBuilder.getName(), pBuilder.getValue());
      
      
      //pTable.addVariableToTable(pBuilder.getName(), pBuilder.getValue());
		
		System.out.println("HERE WE GO PROPERTIES");
		myElementable.addProperty(s, vgData);
//		GameObject object = (GameObject) myElementable; 
		
		propertiesMap.put(s, vgData);
//		object.setProperties(propertiesMap);
		
		for (String property:myElementable.getVoogaProperties().keySet()){
	        System.out.println(property+" "+propertiesMap.get(property).toString());
	    }
		
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
	
	@Override
	public void updateProperties(){
	    System.out.println("Updating properties");
	    GameObject object = (GameObject) myElementable; 
	    for (String property:propertiesMap.keySet()){
	        System.out.println(property+" "+propertiesMap.get(property));
	    }
	    object.setProperties(propertiesMap);
	}



}
