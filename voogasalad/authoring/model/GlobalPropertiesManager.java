package authoring.model;

import java.util.HashMap;
import java.util.Map;

import authoring.interfaces.Elementable;
import javafx.scene.Node;
import tools.VoogaBoolean;
import tools.interfaces.VoogaData;

public class GlobalPropertiesManager implements Elementable {
	
	private Map<String, VoogaData> globalPropertiesMap = new HashMap<String, VoogaData>();
	
	public GlobalPropertiesManager() {
		//used for testing
		globalPropertiesMap.put("RaggityRick", new VoogaBoolean());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public Map<String, VoogaData> getVoogaProperties() {
		return globalPropertiesMap;
	}

	@Override
	public void addProperty(String name, VoogaData data) {
		globalPropertiesMap.put(name, data);
	}

	@Override
	public void removeProperty(String name) {
		globalPropertiesMap.remove(name);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getNodeObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

}
