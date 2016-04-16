/**
 * 
 */
package player.leveldatamanager;

import java.util.Map;

import tools.interfaces.VoogaData;

/**
 * Global Variable manager that keeps track of global variables
 * separately from Elementable objects and Events
 * 
 * @author Hunter Lee
 *
 */
public class GlobalVariableManager {

	private ObjectManager myObjectManager;
	private Map<String, VoogaData> myGlobalVariables;
	
	public GlobalVariableManager(ObjectManager myObjectManager, 
			Map<String, VoogaData> variableObjects) {
		// TODO Auto-generated constructor stub
		this.myObjectManager = myObjectManager;
		this.myGlobalVariables = variableObjects;
	}

	/**
	 * @return the myObjectManager
	 */
	public ObjectManager getObjectManager() {
		return myObjectManager;
	}

	/**
	 * @return the myGlobalVariables
	 */
	public VoogaData getVariable(String name) {
		return myGlobalVariables.get(name);
	}

}