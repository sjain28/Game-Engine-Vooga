/**
 * This entire file is part of my code masterpiece.
 * @author Harry Guo
 * 
 * I just jotted down some example physics to show what types of
 * physics the author could have picked. Through the use of this enum
 * class, developers should be able to create their own physics packages
 * and also make them available for the author to pick from.
 * 
 */

package physics;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import resources.VoogaBundles;

public enum PhysicsEnum {
	STANDARD,
	MOON,
	CRAZY;

	private Map<String, ResourceBundle> physicsBundleMap;

	/**
	 * Instantiates the map of potential physics
	 */
	private PhysicsEnum() {
		physicsBundleMap = new HashMap<>();
		physicsBundleMap.put("STANDARD", VoogaBundles.standardPhysics);
		physicsBundleMap.put("MOON", VoogaBundles.moonPhysics);
		physicsBundleMap.put("CRAZY", VoogaBundles.crazyPhysics);
		
	}

	/**
	 * Gets the color of the enum
	 * @return
	 */
	public ResourceBundle getPhysicsBundle() {
		return this.physicsBundleMap.get(this.name());
	}
}
