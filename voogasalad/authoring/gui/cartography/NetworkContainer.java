package authoring.gui.cartography;

import java.util.Map;

public class NetworkContainer {
	
	private Map<String, String> mappings;
	private Map<String, LevelType> levelTypes;
	
	public NetworkContainer(Map<String, String> mappings, Map<String, LevelType> levelTypes) {
		this.mappings = mappings;
		this.levelTypes = levelTypes;
	}
	
	public Map<String, String> getMappings() {
		return this.mappings;
	}
	
	public Map<String, LevelType> getLevelTypes() {
		return this.levelTypes;
	}

}
