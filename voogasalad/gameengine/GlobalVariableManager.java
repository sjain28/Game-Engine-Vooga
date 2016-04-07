package gameengine;

import java.util.Map;

import tools.interfaces.VoogaData;

/**
 * This class will handle global variables (such as time, game won/lost, etc.)
 **/
public class GlobalVariableManager {
	private Map<String, VoogaData> myVariables;
	
	public GlobalVariableManager(Map<String,VoogaData> variables) {
		myVariables = variables;
	}
	
	public VoogaData getGlobalVar(String variable){
		return myVariables.get(variable);
	}
	
}
