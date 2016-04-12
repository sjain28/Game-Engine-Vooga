package authoring.properties;

/**
 * Tab Manager class to handle all the tabs in the properties TabPane
 * @author Harry Guo, Arjun Desai, Aditya Srinivasan, Nick Lockett
 */

import java.util.ArrayList;

import javafx.scene.control.Tab;

public class PropertiesTabManager {
	
	private ArrayList<Tab> myPropertyTabs = new ArrayList<Tab>();
	private SpritePropertiesTab SPT = new SpritePropertiesTab();
	private GlobalPropertiesTab GPT = new GlobalPropertiesTab();

	public PropertiesTabManager() {
		populateTabList();
	}
	
	public ArrayList<Tab> getTabList() {
		return myPropertyTabs;
	}
	
	private void populateTabList() {
		myPropertyTabs.add(SPT);
		myPropertyTabs.add(GPT);
	}
	
	public SpritePropertiesTab getSpritePropertiesTab() {
		return SPT;
	}
	
	public GlobalPropertiesTab getGlobalPropertiesTab() {
		return GPT;
	}

}
