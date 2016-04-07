package authoring.Properties;

import tools.interfaces.VoogaData;

/*
 * ONLY USED FOR TESTING AS OF NOW, NEED TO IMPLEMENT WITH ELEMENT MANAGER
 */
public class GlobalPropertiesTab extends AbstractPropertiesTab{

	public final static String GLOBAL_PROPERTIES = "Global Properties";
	
	public GlobalPropertiesTab() {
		super();
		this.setText(GLOBAL_PROPERTIES);
	}

	@Override
	public void getPropertiesMap(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewProperty(String s, VoogaData vgData) {
		propertiesMap.put(s, vgData);
		displayProperties();
	}

	@Override
	public void removeProperty(String s) {
		propertiesMap.remove(s);
		displayProperties();
		
	}

}
