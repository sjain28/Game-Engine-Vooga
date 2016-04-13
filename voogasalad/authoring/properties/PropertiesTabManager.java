package authoring.properties;

/**
 * Tab Manager class to handle all the tabs in the properties TabPane
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

	public PropertiesTabManager() {
		selector = ElementSelectionModel.getInstance();
		selector.addObserver(this);
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

	@Override
	public void update(Observable o, Object arg) {
		Elementable e = (Elementable) arg;
		SPT.getPropertiesMap(e);
	}

}
