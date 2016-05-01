package authoring.gui.cartography;

import java.util.Map;

/**
 * Container for all the level data.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */
public class NetworkContainer {

	/**
	 * private instance variables
	 */
	private Map<String, String> mappings;
	private Map<String, LevelType> levelTypes;

	/**
	 * Instantiates the container that contains the mappings.
	 * @param mappings
	 * @param levelTypes
	 */
	public NetworkContainer(Map<String, String> mappings, Map<String, LevelType> levelTypes) {
		this.mappings = mappings;
		this.levelTypes = levelTypes;
	}

	/**
	 * Gets the mappings from level to level.
	 * @return
	 */
	public Map<String, String> getMappings() {
		return this.mappings;
	}

	/**
	 * Gets the map from string to level type.
	 * @return
	 */
	public Map<String, LevelType> getLevelTypes() {
		return this.levelTypes;
	}

}
