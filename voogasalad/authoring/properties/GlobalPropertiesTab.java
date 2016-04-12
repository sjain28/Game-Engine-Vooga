package authoring.Properties;

import authoring.interfaces.Elementable;
import authoring.model.GlobalPropertiesManager;
import tools.interfaces.VoogaData;

/*
 * ONLY USED FOR TESTING AS OF NOW, NEED TO IMPLEMENT WITH ELEMENT MANAGER
 */
public class GlobalPropertiesTab extends AbstractPropertiesTab{

	public final static String GLOBAL_PROPERTIES = "Global Properties";
	private Elementable myElementable;
	
	public GlobalPropertiesTab() {
		super();
		this.setText(GLOBAL_PROPERTIES);
	}

	//Pass in 
	@Override
	public void getPropertiesMap(Object o) {
		myElementable = (Elementable) o;
		propertiesMap = myElementable.getVoogaProperties();
		originalPropertiesMap = myElementable.getVoogaProperties();
		displayProperties();
	}

	@Override
	public void addNewProperty(String s, VoogaData vgData) {
		myElementable.addProperty(s, vgData);
		displayProperties();
	}

	@Override
	public void removeProperty(String s) {
		myElementable.removeProperty(s);
		displayProperties();
		
	}

}
