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

	private OUTDATEDObjectManager myObjectManager;
	private Map<String, VoogaData> myGlobalVariables;
	
	public GlobalVariableManager(OUTDATEDObjectManager myObjectManager, 
			Map<String, VoogaData> variableObjects) {
		// TODO Auto-generated constructor stub
		this.myObjectManager = myObjectManager;
		this.myGlobalVariables = variableObjects;
	}

	/**
	 * @return the myObjectManager
	 */
	public OUTDATEDObjectManager getObjectManager() {
		return myObjectManager;
	}

	/**
	 * @return the myGlobalVariables
	 */
	public VoogaData getVariable(String name) {
		return myGlobalVariables.get(name);
	}

}