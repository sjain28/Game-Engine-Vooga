package authoring.properties;

/**
 * Tab Manager class to handle all the tabs in the properties TabPane
 * 
 * @author Harry Guo, Arjun Desai, Aditya Srinivasan, Nick Lockett
 */

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import authoring.interfaces.Elementable;
import authoring.model.ElementSelectionModel;
import javafx.scene.control.Tab;

public class PropertiesTabManager implements Observer {
	
	private ArrayList<Tab> myPropertyTabs = new ArrayList<Tab>();
	private SpritePropertiesTab SPT = new SpritePropertiesTab();
	private GlobalPropertiesTab GPT = new GlobalPropertiesTab();
	private ElementSelectionModel selector;

	/**
	 * Constructor to instantiate the Singleton class Element Selection Model
	 * for Sprite properties tab
	 */
	public PropertiesTabManager() {
		selector = ElementSelectionModel.getInstance();
		selector.addObserver(this);
		populateTabList();
	}
	
	/**
	 * Returns list of tabs managed by the properties tab manager.
	 * @return
	 */
	public ArrayList<Tab> getTabList() {
		return myPropertyTabs;
	}
	
	/**
	 * Populates the tab list.
	 */
	private void populateTabList() {
		myPropertyTabs.add(SPT);
		myPropertyTabs.add(GPT);
	}
	
	/**
	 * Returns the Sprite Properties Tab.
	 * @return
	 */
	public SpritePropertiesTab getSpritePropertiesTab() {
		return SPT;
	}
	
	/**
	 * Returns the Global Properties Tab.
	 * @return
	 */
	public GlobalPropertiesTab getGlobalPropertiesTab() {
		 return GPT;
	}

	/**
	 * Updates the sprite properties tab based on observing elementals in the 
	 * Design Board.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Elementable e = (Elementable) arg;
		SPT.getPropertiesMap(e);
	}

}
