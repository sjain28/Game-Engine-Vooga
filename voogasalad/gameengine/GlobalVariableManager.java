package gameengine;

import java.util.Map;

import tools.interfaces.VoogaData;

/*
 * This class will handle global variables (such as time, game won/lost, etc.)
 */
public class GlobalVariableManager {

	private Map<String, VoogaData> myVariables;

	public GlobalVariableManager() {

	}
	public VoogaData getVariable(String variableName){
		return myVariables.get(variableName);
	}

}
